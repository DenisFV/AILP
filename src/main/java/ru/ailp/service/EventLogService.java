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

import java.util.Optional;

@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service("eventLogService")
public class EventLogService extends AbstractService<EventLogEntity, EventLogDto, EventLogRepo, EventLogMapper> {

    @NonNull
    static EventLogMapper eventLogMapper = Mappers.getMapper(EventLogMapper.class);
    @NonNull EventService eventService;

    @Autowired
    public EventLogService(EventLogRepo eventLogRepo, EventService eventService) {
        super(eventLogRepo, eventLogMapper);
        this.eventService = eventService;
    }

    public EventLogEventHelper buildEventLogEventHelper(EventLogEventHelper eventLogEventHelper) {
        return EventLogEventHelper.builder()
                .id(eventLogEventHelper.getId())
                .lessonId(eventLogEventHelper.getLessonId())
                .pageId(eventLogEventHelper.getPageId())
                .eventDate(eventLogEventHelper.getEventDate())
                .userId(UserService.getAuthenticationUser().getId())
                .eventDto(eventService.findEventEntityByEventTypeAndEventName(eventLogEventHelper.getEventDto()))
                .build();
    }

    public Optional<EventLogDto> saveEventLogEventHelper(EventLogEventHelper eventLogEventHelper) {
        log.info("Объект на входе: {}", eventLogEventHelper);

        return save(eventLogMapper.eventLogEventHelperToEventLogDto(buildEventLogEventHelper(eventLogEventHelper)));
    }
}