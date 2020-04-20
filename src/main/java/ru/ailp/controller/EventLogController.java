package ru.ailp.controller;

import io.swagger.annotations.Api;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping(value = "/{pageId}/{lessonId}")
    public ResponseEntity<EntityModel<EventLogDto>> saveEventLogEventHelper(
            @PathVariable("pageId") Long pageId,
            @PathVariable("lessonId") Long lessonId,
            @RequestBody EventLogEventHelper eventLogEventHelper
    ) {

        eventLogEventHelper.setPageId(pageId);
        eventLogEventHelper.setLessonId(lessonId);

        return eventLogService.saveEventLogEventHelper(eventLogEventHelper)
                .map(e -> ResponseEntity.ok(link.toModel(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
