package ru.ailp.controller.abstr;

import io.swagger.annotations.ApiParam;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ailp.controller.link.DefaultLink;
import ru.ailp.entity.abstr.AbstractEntity;
import ru.ailp.service.abstr.CommonService;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractController<T extends AbstractEntity, S extends CommonService<T>> implements CommonController<T> {

    @NonNull
    final S service;
    DefaultLink<T> link = new DefaultLink<>(this);

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.HEAD)
    public ResponseEntity head(@PathVariable Long id) {
        log.info("Проверка сущетсвования объекта по id");

        return service.findById(id)
                .map(exists -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id:[\\d]+}")
    public ResponseEntity<EntityModel<T>> findById(@ApiParam(value = "Индентификатор объекта", example = "1") @PathVariable Long id) {
        log.info("Поиск объекта по id");

        return service.findById(id)
                .map(e -> ResponseEntity.ok(link.toModel(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<T>>> findAll() {
        log.info("Поиск всех объектов");

        return service.findAll()
                .map(e -> ResponseEntity.ok(link.toCollectionModel(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<EntityModel<T>> save(@RequestBody T t) {
        log.info("Сохранение / Обновление объекта: {}", t);

        return service.save(t)
                .map(e -> ResponseEntity.ok(link.toModel(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id:[\\d]+}")
    public ResponseEntity<EntityModel<T>> deleteById(@ApiParam(value = "Индентификатор объекта", example = "1") @PathVariable Long id) {
        log.info("Удаление объекта по id: {}", id);

        return service.deleteById(id)
                .map(e -> ResponseEntity.ok(link.toModel(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<T>>> deleteAll() {
        log.info("Удаление всех объектов");

        return service.deleteAll()
                .map(e -> ResponseEntity.ok(link.toCollectionModel(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}