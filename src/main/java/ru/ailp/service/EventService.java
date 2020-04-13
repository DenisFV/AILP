package ru.ailp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ailp.dto.EventDto;
import ru.ailp.entity.EventEntity;
import ru.ailp.mapper.EventMapper;
import ru.ailp.repo.EventRepo;
import ru.ailp.service.abstr.AbstractService;

import javax.ws.rs.NotFoundException;

@Service("eventService")
public class EventService extends AbstractService<EventEntity, EventDto, EventRepo, EventMapper> {

    private static final Logger logger = LogManager.getLogger(EventService.class);
    private static final EventMapper eventMapper = Mappers.getMapper(EventMapper.class);
    private final EventRepo eventRepo;

    @Autowired
    public EventService(EventRepo eventRepo) {
        super(eventRepo, eventMapper);
        this.eventRepo = eventRepo;
    }

    EventEntity findEventEntityByEventTypeAndEventName(EventEntity eventEntity) {
        logger.info("Объекта на входе: {}", eventEntity);

        return eventRepo.findEventEntityByEventTypeAndEventName(eventEntity.getEventType(), eventEntity.getEventName())
                .orElseThrow(() -> new NotFoundException("Такого события не существует"));
    }
}