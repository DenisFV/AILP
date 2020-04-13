package ru.ailp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ailp.dto.EventLogDto;
import ru.ailp.entity.EventLogEntity;
import ru.ailp.entity.helper.EventLogHelper;
import ru.ailp.mapper.EventLogMapper;
import ru.ailp.repo.EventLogRepo;
import ru.ailp.service.abstr.AbstractService;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

@Service("eventLogService")
public class EventLogService extends AbstractService<EventLogEntity, EventLogDto, EventLogRepo, EventLogMapper> {

    private static final Logger logger = LogManager.getLogger(EventLogService.class);
    private static final EventLogMapper eventLogMapper = Mappers.getMapper(EventLogMapper.class);
    private final EventService eventService;

    @Autowired
    public EventLogService(EventLogRepo eventLogRepo, EventService eventService) {
        super(eventLogRepo, eventLogMapper);
        this.eventService = eventService;
    }

    public Optional<EventLogDto> saveEventLogHelper(EventLogHelper eventLogHelper) {
        logger.info("Объект на входе: {}", eventLogHelper);

        eventLogHelper.setUserId(UserService.getAuthenticationUser().getId());

        eventLogHelper.setEventEntity(
                Optional.ofNullable(eventLogHelper.getEventEntity())
                        .map(eventService::findEventEntityByEventTypeAndEventName)
                        .orElseThrow(() -> new NotFoundException("Событие не указано"))
        );

        return save(eventLogMapper.eventLogHelperToEventLogDto(eventLogHelper));
    }
}