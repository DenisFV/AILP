package ru.ailp.service.abstr;

import ru.ailp.entity.abstr.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface CommonService<T extends AbstractEntity> {

    Optional<T> findById(Long id);

    Optional<List<T>> findAll();

    Optional<T> save(T t);

    Optional<List<T>> saveAll(List<T> tList);

    T update(T t);

    T create(T t);

    Optional<T> deleteById(Long id);

    Optional<List<T>> deleteAll();
}