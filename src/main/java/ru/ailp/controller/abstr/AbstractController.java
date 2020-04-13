package ru.ailp.controller.abstr;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ailp.entity.abstr.AbstractEntity;
import ru.ailp.service.abstr.CommonService;

import java.util.List;

public abstract class AbstractController<T extends AbstractEntity, S extends CommonService<T>> implements CommonController<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);
    protected S service;

    public AbstractController(S service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.HEAD)
    public ResponseEntity head(@PathVariable Long id) {
        logger.info("Проверка сущетсвования объекта по id");

        return service.findById(id)
                .map(exists -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id:[\\d]+}")
    public ResponseEntity<T> findById(@ApiParam(value = "Индентификатор объекта", example = "1") @PathVariable Long id) {
        logger.info("Поиск объекта по id");

        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<List<T>> findAll() {
        logger.info("Поиск всех объектов");

        return service.findAll()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<T> save(@RequestBody T t) {
        logger.info("Сохранение / Обновление объекта: {}", t);

        return service.save(t)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id:[\\d]+}")
    public ResponseEntity<T> deleteById(@ApiParam(value = "Индентификатор объекта", example = "1") @PathVariable Long id) {
        logger.info("Удаление объекта по id: {}", id);

        return service.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/")
    public ResponseEntity<List<T>> deleteAll() {
        logger.info("Удаление всех объектов");

        return service.deleteAll()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}