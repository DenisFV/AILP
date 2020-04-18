package ru.ailp.controller.abstr;

import io.swagger.annotations.ApiParam;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ailp.entity.abstr.AbstractEntity;

public interface CommonController<T extends AbstractEntity> {

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.HEAD)
    ResponseEntity head(@PathVariable Long id);

    @GetMapping("/{id:[\\d]+}")
    ResponseEntity<EntityModel<T>> findById(@ApiParam(value = "Индентификатор объекта", example = "1") @PathVariable Long id);

    @GetMapping("/")
    ResponseEntity<CollectionModel<EntityModel<T>>> findAll();

    @PostMapping("/")
    ResponseEntity<EntityModel<T>> save(@RequestBody T t);

    @DeleteMapping("/{id:[\\d]+}")
    ResponseEntity<EntityModel<T>> deleteById(@ApiParam(value = "Индентификатор объекта", example = "1") @PathVariable Long id);

    @DeleteMapping("/")
    ResponseEntity<CollectionModel<EntityModel<T>>> deleteAll();

    @GetMapping("/test")
    ResponseEntity test();
}