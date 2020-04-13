package ru.ailp.controller.abstr;

import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ailp.entity.abstr.AbstractEntity;

import java.util.List;

public interface CommonController<T extends AbstractEntity> {

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.HEAD)
    ResponseEntity head(@PathVariable Long id);

    @GetMapping("/{id:[\\d]+}")
    ResponseEntity<T> findById(@ApiParam(value = "Индентификатор объекта", example = "1") @PathVariable Long id);

    @GetMapping("/")
    ResponseEntity<List<T>> findAll();

    @PostMapping("/")
    ResponseEntity<T> save(@RequestBody T t);

    @DeleteMapping("/{id:[\\d]+}")
    ResponseEntity<T> deleteById(@ApiParam(value = "Индентификатор объекта", example = "1") @PathVariable Long id);

    @DeleteMapping("/")
    ResponseEntity<List<T>> deleteAll();
}