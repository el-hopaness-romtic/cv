package ru.yandex.enrollment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.enrollment.domain.SystemItem;
import ru.yandex.enrollment.dto.SystemItemImport;
import ru.yandex.enrollment.dto.SystemItemImportRequest;
import ru.yandex.enrollment.enums.SystemItemType;
import ru.yandex.enrollment.repository.SystemItemRepository;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SystemItemService {

    private final SystemItemRepository repository;

    public SystemItem find(String id) {
        SystemItem item = repository.findByItemId(id).orElseThrow(() -> new NoSuchElementException("Не найдено элемента с id " + id));
        if (item.getType() == SystemItemType.FOLDER) {
            Map<Long, SystemItem> idMap = new HashMap<>();

            repository.getChildrenRecursively(item.getId()).forEach(i -> idMap.put(i.getId(), i));
            idMap.forEach((key, value) -> idMap.getOrDefault(value.getParentId(), item).getChildren().add(value));
        }
        return item;
    }

    @Transactional(readOnly = true)
    public List<SystemItem> findFilesBetween(ZonedDateTime start, ZonedDateTime end) {
        List<SystemItem> items = repository.findByDateBetweenAndTypeIs(start, end, SystemItemType.FILE);
        items.stream()
                .filter(item -> item.getParentId() != null)
                .forEach(item -> item.setParentItemId(repository.getItemIdById(item.getParentId())));
        return items;
    }

    public void deleteByItemId(String id) {
        SystemItem item = repository.findByItemId(id).orElseThrow(() -> new NoSuchElementException("Не найдено элемента с id " + id));
        repository.delete(item);
    }

    @Transactional
    public void imports(SystemItemImportRequest request) {
        validateSystemItemImports(request.getItems());

        Map<Boolean, List<SystemItemImport>> groupedItems = request.getItems().stream()
                .collect(Collectors.partitioningBy(a -> a.getType() == SystemItemType.FOLDER));
        updateFolders(groupedItems.get(true), request.getUpdateDate());
        updateFiles(groupedItems.get(false), request.getUpdateDate());
    }

    private void updateFiles(List<SystemItemImport> files, ZonedDateTime updateDate) {
        for (SystemItemImport i : files) {
            if (i.getType() == SystemItemType.FILE) {
                SystemItem build = SystemItem.builder()
                        .id(getIdByItemIdElseNull(i.getItemId()))
                        .parentId(getIdByItemIdElseNull(i.getParentItemId()))
                        .itemId(i.getItemId())
                        .url(i.getUrl())
                        .date(updateDate)
                        .type(SystemItemType.FILE)
                        .size(i.getSize())
                        .build();
                repository.save(build);
            }
        }
    }

    private void updateFolders(List<SystemItemImport> folders, ZonedDateTime updateDate) {
        Map<String, Entry<SystemItemImport, Long>> priority = new HashMap<>();

        folders.forEach(i -> priority.put(i.getItemId(), Map.entry(i, 0L)));
        folders.forEach(i -> {
            var pair = priority.getOrDefault(i.getParentItemId(), null);
            while (pair != null) {
                pair.setValue(pair.getValue() + 1);
                pair = priority.getOrDefault(pair.getKey().getParentItemId(), null);
            }
        });
        priority.values()
                .stream()
                .sorted(Entry.<SystemItemImport, Long>comparingByValue().reversed())
                .map(Entry::getKey)
                .forEachOrdered(i ->
                        repository.save(SystemItem.builder()
                                .date(updateDate)
                                .id(getIdByItemIdElseNull(i.getItemId()))
                                .parentId(getIdByItemIdElseNull(i.getParentItemId()))
                                .itemId(i.getItemId())
                                .type(SystemItemType.FOLDER)
                                .build())
                );
    }

    private void validateSystemItemImports(List<SystemItemImport> items) {
        HashSet<String> ids = new HashSet<>(), parentIds = new HashSet<>(), folders = new HashSet<>();

        for (SystemItemImport item : items) {
            if (!ids.add(item.getItemId()))
                throw new IllegalArgumentException("В списке не должно быть элементов в с повторяющимся id (id = %s)".formatted(item.getItemId()));

            validateSizeAndUrl(item);
            validateItemType(item);

            if (item.getType() == SystemItemType.FOLDER)
                folders.add(item.getItemId());

            parentIds.add(item.getParentItemId());
        }
        validateParentsExist(parentIds, folders);
    }

    private void validateSizeAndUrl(SystemItemImport item) {
        if (item.getType() == SystemItemType.FILE) {
            if (item.getSize() == null)
                throw new IllegalArgumentException("У файла не может быть size = null (id = %s)".formatted(item.getItemId()));

            if (item.getUrl() == null)
                throw new IllegalArgumentException("У файла не может быть url = null (id = %s)".formatted(item.getItemId()));
        } else {
            if (item.getSize() != null)
                throw new IllegalArgumentException("У папки должен быть size = null (id = %s)".formatted(item.getItemId()));

            if (item.getUrl() != null)
                throw new IllegalArgumentException("У папки должен быть size = null (id = %s)".formatted(item.getItemId()));
        }
    }

    private void validateItemType(SystemItemImport item) {
        if (item.getType() == SystemItemType.FILE) {
            if (repository.existsByItemIdAndType(item.getItemId(), SystemItemType.FOLDER))
                throw new IllegalArgumentException("Изменение типа элемента с файла на папку не допускается (id = %s)".formatted(item.getItemId()));
        } else {
            if (repository.existsByItemIdAndType(item.getItemId(), SystemItemType.FILE))
                throw new IllegalArgumentException("Изменение типа элемента с папки на файл не допускается (id = %s)".formatted(item.getItemId()));
        }
    }

    private void validateParentsExist(Set<String> parentIds, Set<String> folders) {
        parentIds.remove(null);
        for (String parentId : parentIds) {
            if (!(folders.contains(parentId) || repository.existsByItemIdAndType(parentId, SystemItemType.FOLDER)))
                throw new IllegalArgumentException("Нет папки с id = %s".formatted(parentId));
        }
    }

    private Long getIdByItemIdElseNull(String id) {
        if (id == null) return null;
        return repository.getIdByItemId(id);
    }

}
