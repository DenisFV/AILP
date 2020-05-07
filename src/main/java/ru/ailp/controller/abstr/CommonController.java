package ru.ailp.controller.abstr;

import io.swagger.annotations.ApiParam;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.ailp.entity.abstr.AbstractEntity;

public interface CommonController<T extends AbstractEntity> {

    ResponseEntity head(@PathVariable Long id);

    ResponseEntity<EntityModel<T>> findById(@ApiParam(value = "Индентификатор объекта", example = "1") @PathVariable Long id);

    ResponseEntity<CollectionModel<EntityModel<T>>> findAll();

    ResponseEntity<EntityModel<T>> save(@RequestBody T t);

    ResponseEntity<EntityModel<T>> deleteById(@ApiParam(value = "Индентификатор объекта", example = "1") @PathVariable Long id);

    ResponseEntity<CollectionModel<EntityModel<T>>> deleteAll();

    ResponseEntity test();
}