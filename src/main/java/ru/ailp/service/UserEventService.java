package ru.ailp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ailp.dto.UserEventDto;
import ru.ailp.mapper.UserEventMapper;
import ru.ailp.repo.UserEventRepo;

import java.util.List;
import java.util.Optional;

@Service("catalogService")
public class UserEventService {

    private static final Logger logger = LogManager.getLogger(UserEventService.class);
    private static final UserEventMapper redisMapper = Mappers.getMapper(UserEventMapper.class);
    private final UserEventRepo userEventRepo;

    @Autowired
    public UserEventService(UserEventRepo userEventRepo) {
        this.userEventRepo = userEventRepo;
    }

    public Optional<UserEventDto> findById(Long id) {
        logger.info("id объекта на входе: {}", id);
        return Optional.ofNullable(userEventRepo.findById(id)
                .map(redisMapper::userEventEntityToUserEventDto)
                .orElseGet(() -> {
                    logger.error("Объекта не сущетсвует");
                    return null;
                }));
    }

    public Optional<List<UserEventDto>> findAll() {
        List<UserEventDto> userEventDtos = redisMapper.userEventEntityListToUserEventDtoList(userEventRepo.findAll());
        if (userEventDtos.isEmpty()) {
            logger.error("Объекта не сущетсвует");
            return Optional.empty();
        } else {
            return Optional.of(userEventDtos);
        }
    }

    public Optional<UserEventDto> save(UserEventDto userEventDto) {
        logger.info("Объект на входе: {}", userEventDto);

        return Optional.ofNullable(Optional.ofNullable(userEventDto.getUserEventId())
                .map(e -> {
                    logger.info("Обновление объекта");
                    return userEventRepo.findById(userEventDto.getUserEventId())
                            .map(k -> redisMapper.userEventEntityToUserEventDto(userEventRepo.save(redisMapper.userEventDtoToUserEventEntity(userEventDto))))
                            .orElseGet(() -> {
                                logger.error("Ошибка при попытке обновить запись (Объекта с таким id не сущетсвует)");
                                return null;
                            });
                })
                .orElseGet(() -> {
                    logger.info("Создание нового объекта");
                    return redisMapper.userEventEntityToUserEventDto(userEventRepo.save(redisMapper.userEventDtoToUserEventEntity(userEventDto)));
                }));
    }

    public Optional<UserEventDto> deleteById(Long id) {
        logger.info("id объекта на входе: {}", id);
        return userEventRepo.findById(id)
                .map(e -> {
                    userEventRepo.delete(e);
                    logger.info("Запись с id: {} успешно удалена из БД", id);
                    return Optional.of(redisMapper.userEventEntityToUserEventDto(e));
                })
                .orElseGet(() -> {
                    logger.info("Не удалось удалить запись с id: {} (Объекта с таким id не сущетсвует)", id);
                    return Optional.empty();
                });
    }

    public Optional<List<UserEventDto>> deleteAll() {
        try {
            List<UserEventDto> userEventDtoList = redisMapper.userEventEntityListToUserEventDtoList(userEventRepo.findAll());
            userEventRepo.deleteAll();
            logger.info("Все записи успешно удалены из БД");
            return Optional.of(userEventDtoList);
        } catch (Exception e) {
            logger.info("Не удалось удалить все записи: {}", e.getMessage());
            return Optional.empty();
        }
    }
}