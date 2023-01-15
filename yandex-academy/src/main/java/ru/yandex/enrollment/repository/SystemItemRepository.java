package ru.yandex.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.yandex.enrollment.domain.SystemItem;
import ru.yandex.enrollment.enums.SystemItemType;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;


public interface SystemItemRepository extends JpaRepository<SystemItem, Long> {

    Optional<SystemItem> findByItemId(String itemId);

    boolean existsByItemIdAndType(String itemId, SystemItemType type);

    @Query("select id from SystemItem where itemId = ?1")
    Long getIdByItemId(String id);

    @Query("select itemId from SystemItem where id = ?1")
    String getItemIdById(Long id);

    List<SystemItem> findByDateBetweenAndTypeIs(ZonedDateTime from, ZonedDateTime to, SystemItemType file);

    @Query(value = """
            WITH RECURSIVE c AS (
               SELECT *
               FROM system_item AS si1
               WHERE id = ?1
               UNION ALL
               SELECT si.*
               FROM system_item AS si
               JOIN c ON c.id = si.parent_id
            )
            SELECT * FROM c WHERE id <> ?1
            """, nativeQuery = true)
    List<SystemItem> getChildrenRecursively(long id);
}
