package ru.ailp.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ailp.controller.abstr.AbstractController;
import ru.ailp.dto.EventLogDto;
import ru.ailp.entity.helper.EventLogHelper;
import ru.ailp.service.EventLogService;

@RestController
@RequestMapping("event-log")
@Api(tags = "EventLogController", description = "EventLogController")
public class EventLogController extends AbstractController<EventLogDto, EventLogService> {

    private EventLogService eventLogService;

    @Autowired
    public EventLogController(EventLogService eventLogService) {
        super(eventLogService);
        this.eventLogService = eventLogService;
    }

    @PostMapping(value = "/{pageId}/{lessonId}")
    public ResponseEntity<EventLogDto> saveEventLogHelper(
            @PathVariable("pageId") Long pageId,
            @PathVariable("lessonId") Long lessonId,
            @RequestBody EventLogHelper eventLogHelper
    ) {

        eventLogHelper.setPageId(pageId);
        eventLogHelper.setLessonId(lessonId);

        return eventLogService.saveEventLogHelper(eventLogHelper)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
