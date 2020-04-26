package ru.ailp.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ailp.dto.EventDto;
import ru.ailp.entity.EventEntity;
import ru.ailp.mapper.EventMapper;
import ru.ailp.repo.EventRepo;
import ru.ailp.service.abstr.AbstractService;

import javax.ws.rs.NotFoundException;

@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service("eventService")
public class EventService extends AbstractService<EventEntity, EventDto, EventRepo, EventMapper> {

    @NonNull
    static EventMapper eventMapper = Mappers.getMapper(EventMapper.class);
    @NonNull EventRepo eventRepo;

    @Autowired
    public EventService(EventRepo eventRepo) {
        super(eventRepo, eventMapper);
        this.eventRepo = eventRepo;
    }

    EventDto findEventEntityByEventTypeAndEventName(EventDto eventDto) {
        log.info("Объекта на входе: {}", eventDto);

        return eventRepo.findEventEntityByEventTypeAndEventName(eventDto.getEventType(), eventDto.getEventName())
                .map(eventMapper::entityToDto)
                .orElseThrow(() -> new NotFoundException("Такого события не существует"));
    }
}