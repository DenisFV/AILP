package ru.ailp.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ailp.dto.EventLogDto;
import ru.ailp.dto.helper.EventLogEventHelper;
import ru.ailp.entity.EventLogEntity;
import ru.ailp.mapper.EventLogMapper;
import ru.ailp.repo.EventLogRepo;
import ru.ailp.service.abstr.AbstractService;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service("eventLogService")
public class EventLogService extends AbstractService<EventLogEntity, EventLogDto, EventLogRepo, EventLogMapper> {

    @NonNull
    private static EventLogMapper eventLogMapper = Mappers.getMapper(EventLogMapper.class);
    @NonNull EventService eventService;

    @Autowired
    public EventLogService(EventLogRepo eventLogRepo, EventService eventService) {
        super(eventLogRepo, eventLogMapper);
        this.eventService = eventService;
    }

    public Optional<EventLogDto> saveEventLogEventHelper(EventLogEventHelper eventLogEventHelper) {
        log.info("Объект на входе: {}", eventLogEventHelper);

        eventLogEventHelper.setUserId(UserService.getAuthenticationUser().getId());

        eventLogEventHelper.setEventDto(
                Optional.ofNullable(eventLogEventHelper.getEventDto())
                        .map(eventService::findEventEntityByEventTypeAndEventName)
                        .orElseThrow(() -> new NotFoundException("Событие не указано"))
        );

        return save(eventLogMapper.eventLogEventHelperToEventLogDto(eventLogEventHelper));
    }
}