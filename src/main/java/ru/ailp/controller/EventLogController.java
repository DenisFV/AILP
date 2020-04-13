package ru.ailp.controller;

import io.swagger.annotations.Api;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ailp.controller.abstr.AbstractController;
import ru.ailp.dto.EventLogDto;
import ru.ailp.dto.helper.EventLogHelper;
import ru.ailp.service.EventLogService;

@RestController
@RequestMapping("event-log")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Api(tags = "EventLogController", description = "EventLogController")
public class EventLogController extends AbstractController<EventLogDto, EventLogService> {

    @NonNull EventLogService eventLogService;

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
