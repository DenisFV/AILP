package ru.ailp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ailp.dto.EventLogDto;
import ru.ailp.mapper.EventLogMapper;
import ru.ailp.repo.EventLogRepo;

import java.util.List;
import java.util.Optional;

@Service("catalogService")
public class EventLogService {

    private static final Logger logger = LogManager.getLogger(EventLogService.class);
    private static final EventLogMapper redisMapper = Mappers.getMapper(EventLogMapper.class);
    private final EventLogRepo eventLogRepo;

    @Autowired
    public EventLogService(EventLogRepo eventLogRepo) {
        this.eventLogRepo = eventLogRepo;
    }

    public Optional<EventLogDto> findById(Long id) {
        logger.info("id объекта на входе: {}", id);
        return Optional.ofNullable(eventLogRepo.findById(id)
                .map(redisMapper::eventLogEntityToEventLogDto)
                .orElseGet(() -> {
                    logger.error("Объекта не сущетсвует");
                    return null;
                }));
    }

    public Optional<List<EventLogDto>> findAll() {
        List<EventLogDto> eventLogDtos = redisMapper.eventLogEntityListToEventLogDtoList(eventLogRepo.findAll());
        if (eventLogDtos.isEmpty()) {
            logger.error("Объекта не сущетсвует");
            return Optional.empty();
        } else {
            return Optional.of(eventLogDtos);
        }
    }

    public Optional<EventLogDto> save(EventLogDto eventLogDto) {
        logger.info("Объект на входе: {}", eventLogDto);

        return Optional.ofNullable(Optional.ofNullable(eventLogDto.getEventLogId())
                .map(e -> {
                    logger.info("Обновление объекта");
                    return eventLogRepo.findById(eventLogDto.getEventLogId())
                            .map(k -> redisMapper.eventLogEntityToEventLogDto(eventLogRepo.save(redisMapper.eventLogDtoToEventLogEntity(eventLogDto))))
                            .orElseGet(() -> {
                                logger.error("Ошибка при попытке обновить запись (Объекта с таким id не сущетсвует)");
                                return null;
                            });
                })
                .orElseGet(() -> {
                    logger.info("Создание нового объекта");
                    return redisMapper.eventLogEntityToEventLogDto(eventLogRepo.save(redisMapper.eventLogDtoToEventLogEntity(eventLogDto)));
                }));
    }

    public Optional<EventLogDto> deleteById(Long id) {
        logger.info("id объекта на входе: {}", id);
        return eventLogRepo.findById(id)
                .map(e -> {
                    eventLogRepo.delete(e);
                    logger.info("Запись с id: {} успешно удалена из БД", id);
                    return Optional.of(redisMapper.eventLogEntityToEventLogDto(e));
                })
                .orElseGet(() -> {
                    logger.info("Не удалось удалить запись с id: {} (Объекта с таким id не сущетсвует)", id);
                    return Optional.empty();
                });
    }

    public Optional<List<EventLogDto>> deleteAll() {
        try {
            List<EventLogDto> eventLogDtoList = redisMapper.eventLogEntityListToEventLogDtoList(eventLogRepo.findAll());
            eventLogRepo.deleteAll();
            logger.info("Все записи успешно удалены из БД");
            return Optional.of(eventLogDtoList);
        } catch (Exception e) {
            logger.info("Не удалось удалить все записи: {}", e.getMessage());
            return Optional.empty();
        }
    }
}