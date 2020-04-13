package ru.ailp.service.abstr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ailp.entity.abstr.AbstractEntity;
import ru.ailp.mapper.abstr.CommonMapper;
import ru.ailp.repo.abstr.CommonRepo;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends AbstractEntity, D extends AbstractEntity, R extends CommonRepo<T>, M extends CommonMapper<T, D>> implements CommonService<D> {

    private static final Logger logger = LogManager.getLogger(AbstractService.class);
    private R repo;
    private M mapper;

    public AbstractService(R repo, M mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Optional<D> findById(Long id) {
        logger.info("id объекта на входе: {}", id);

        return Optional.ofNullable(repo.findById(id)
                .map(mapper::entityToDto)
                .orElseGet(() -> {
                    logger.error("Объекта с id: {} не сущетсвует", id);
                    return null;
                }));
    }

    @Override
    public Optional<List<D>> findAll() {
        List<D> list = mapper.entityListToDtoList(repo.findAll());
        if (list.isEmpty()) {
            logger.error("Объектов не сущетсвует");
            return Optional.empty();
        } else {
            return Optional.of(list);
        }
    }

    @Override
    public Optional<D> save(D d) {
        logger.info("Объект на входе: {}", d);

        return Optional.ofNullable(
                Optional.ofNullable(d.getId())
                        .map(e -> update(d))
                        .orElseGet(() -> add(d))
        );
    }

    @Override
    public D update(D d) {
        logger.info("Обновление объекта");

        return repo.findById(d.getId())
                .map(k -> mapper.entityToDto(repo.save(mapper.dtoToEntity(d))))
                .orElseGet(() -> {
                    logger.error("Ошибка при попытке обновить запись (Объекта с id: {} не сущетсвует)", d.getId());
                    return null;
                });
    }

    @Override
    public D add(D d) {
        logger.info("Создание нового объекта");

        return mapper.entityToDto(repo.save(mapper.dtoToEntity(d)));
    }

    @Override
    public Optional<D> deleteById(Long id) {
        logger.info("id объекта на входе: {}", id);

        return repo.findById(id)
                .map(e -> {
                    repo.delete(e);
                    logger.info("Запись с id: {} успешно удалена", id);
                    return Optional.of(mapper.entityToDto(e));
                })
                .orElseGet(() -> {
                    logger.info("Не удалось удалить запись с id: {} (Объекта с таким id не сущетсвует)", id);
                    return Optional.empty();
                });
    }

    @Override
    public Optional<List<D>> deleteAll() {
        try {
            List<D> list = mapper.entityListToDtoList(repo.findAll());
            repo.deleteAll();
            logger.info("Все записи успешно удалены");
            return Optional.of(list);
        } catch (Exception e) {
            logger.info("Не удалось удалить все записи: {}", e.getMessage());
            return Optional.empty();
        }
    }
}