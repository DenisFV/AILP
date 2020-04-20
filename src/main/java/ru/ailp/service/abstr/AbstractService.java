package ru.ailp.service.abstr;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import ru.ailp.entity.abstr.AbstractEntity;
import ru.ailp.mapper.abstr.CommonMapper;
import ru.ailp.repo.abstr.CommonRepo;

import java.util.List;
import java.util.Optional;

@Slf4j
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
@RequiredArgsConstructor
public abstract class AbstractService<T extends AbstractEntity, D extends AbstractEntity, R extends CommonRepo<T>,
        M extends CommonMapper<T, D>> implements CommonService<D> {

    @NonNull R repo;
    @NonNull M mapper;

    @Override
    public Optional<D> findById(Long id) {
        log.info("id объекта на входе: {}", id);

        return Optional.ofNullable(repo.findById(id)
                .map(mapper::entityToDto)
                .orElseGet(() -> {
                    log.error("Объекта с id: {} не сущетсвует", id);
                    return null;
                }));
    }

    @Override
    public Optional<List<D>> findAll() {
        List<D> list = mapper.entityListToDtoList(repo.findAll());
        if (list.isEmpty()) {
            log.error("Объектов не сущетсвует");
            return Optional.empty();
        } else {
            return Optional.of(list);
        }
    }

    @Override
    public Optional<D> save(D d) {
        log.info("Объект на входе: {}", d);

        return Optional.ofNullable(
                Optional.ofNullable(d.getId())
                        .map(e -> update(d))
                        .orElseGet(() -> create(d))
        );
    }

    @Override
    public D update(D d) {
        log.info("Обновление объекта");

        return repo.findById(d.getId())
                .map(k -> mapper.entityToDto(repo.save(mapper.dtoToEntity(d))))
                .orElseGet(() -> {
                    log.error("Ошибка при попытке обновить запись (Объекта с id: {} не сущетсвует)", d.getId());
                    return null;
                });
    }

    @Override
    public D create(D d) {
        log.info("Создание нового объекта");

        return mapper.entityToDto(repo.save(mapper.dtoToEntity(d)));
    }

    @Override
    public Optional<D> deleteById(Long id) {
        log.info("id объекта на входе: {}", id);

        return repo.findById(id)
                .map(e -> {
                    repo.delete(e);
                    log.info("Запись с id: {} успешно удалена", id);
                    return Optional.of(mapper.entityToDto(e));
                })
                .orElseGet(() -> {
                    log.info("Не удалось удалить запись с id: {} (Объекта с таким id не сущетсвует)", id);
                    return Optional.empty();
                });
    }

    @Override
    public Optional<List<D>> deleteAll() {
        try {
            List<D> list = mapper.entityListToDtoList(repo.findAll());
            repo.deleteAll();
            log.info("Все записи успешно удалены");
            return Optional.of(list);
        } catch (Exception e) {
            log.info("Не удалось удалить все записи: {}", e.getMessage());
            return Optional.empty();
        }
    }
}