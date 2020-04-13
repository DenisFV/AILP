package ru.ailp.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ailp.controller.abstr.AbstractController;
import ru.ailp.dto.EventDto;
import ru.ailp.service.EventService;

@RestController
@RequestMapping("event")
@Api(tags = "EventController", description = "EventController")
public class EventController extends AbstractController<EventDto, EventService> {

    @Autowired
    public EventController(EventService eventService) {
        super(eventService);
    }
}
