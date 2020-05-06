package ru.ailp.controller;

import io.swagger.annotations.Api;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ailp.controller.abstr.AbstractController;
import ru.ailp.controller.link.DefaultLink;
import ru.ailp.controller.link.EventLogLink;
import ru.ailp.dto.EventLogDto;
import ru.ailp.dto.helper.EventLogEventHelper;
import ru.ailp.service.EventLogService;

@RestController
@RequestMapping("event-log")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(tags = "EventLogController", description = "EventLogController")
public class EventLogController extends AbstractController<EventLogDto, EventLogService> {

    @NonNull EventLogService eventLogService;
    @NonNull DefaultLink<EventLogDto> link = new EventLogLink(this);

    @Autowired
    public EventLogController(EventLogService eventLogService) {
        super(eventLogService);
        this.eventLogService = eventLogService;
    }

    @PostMapping(value = "/1/1")
    public ResponseEntity<EntityModel<EventLogDto>> saveEventLogEventHelper(@RequestBody EventLogEventHelper eventLogEventHelper) {

        return eventLogService.saveEventLogEventHelper(eventLogEventHelper)
                .map(e -> ResponseEntity.ok(link.toModel(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
