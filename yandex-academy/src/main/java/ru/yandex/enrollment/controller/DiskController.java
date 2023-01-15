package ru.yandex.enrollment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.enrollment.domain.SystemItem;
import ru.yandex.enrollment.dto.SystemItemImportRequest;
import ru.yandex.enrollment.service.SystemItemService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DiskController {
    private final SystemItemService service;

    @PostMapping("/imports")
    ResponseEntity<String> imports(@RequestBody @Valid SystemItemImportRequest request) {
        service.imports(request);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable("id") @Valid String itemId) {
        service.deleteByItemId(itemId);
        return ResponseEntity.ok("");
    }

    @GetMapping("/updates")
    ResponseEntity<List<SystemItem>> getUpdatedFilesInDay(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam("date")
            @NotNull
                    ZonedDateTime date) {
        return ResponseEntity.ok(service.findFilesBetween(date.minusDays(1), date));
    }

    @GetMapping("/nodes/{id}")
    ResponseEntity<SystemItem> get(@PathVariable("id") @NotNull String id) {
        return ResponseEntity.ok(service.find(id));
    }
}

