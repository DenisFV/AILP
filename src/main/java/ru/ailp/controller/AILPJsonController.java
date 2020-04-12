package ru.ailp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.ailp.dto.EventLogDto;
import ru.ailp.entity.EventsEntity;
import ru.ailp.entity.UsersEntity;
import ru.ailp.repo.EventsRepo;
import ru.ailp.service.EventLogService;

import java.util.List;

@RestController
@RequestMapping("ailp")
@Api(tags = {"AILPJsonController"}, description = "Ресты AILP", produces = MediaType.APPLICATION_JSON_VALUE)
public class AILPJsonController {

    private static final Logger logger = LoggerFactory.getLogger(AILPJsonController.class);
    @Autowired
    private EventLogService eventLogService;

    @Autowired
    private EventsRepo eventsRepo;

    @PostMapping(value = "/{pageId}/{lessonId}")
    public ResponseEntity<EventLogDto> saves(@PathVariable("pageId") Long pageId, @PathVariable("lessonId") Long lessonId, @RequestBody EventLogDto eventLogDto) {
        Long userId = -1L;
        try {
            userId = ((UsersEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
        } catch (Exception e) {
            System.out.println("Юзер не определен");
        }
        eventLogDto.setUserId(userId);
        eventLogDto.setPageId(pageId);
        eventLogDto.setLessonId(lessonId);

        return eventLogService.save(eventLogDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/event")
    public ResponseEntity<EventsEntity> getEventsIdbyTypeAndName(@RequestBody EventsEntity eventsEntity) {
        return eventsRepo.getEventsEntityByEventTypeAndEventName(eventsEntity.getEventType(), eventsEntity.getEventName())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/{id:[\\d]+}")
    public ResponseEntity<EventLogDto> findById(@ApiParam(value = "Индентификатор объекта EventLogDto", example = "1") @PathVariable Long id) {
        return eventLogService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<List<EventLogDto>> findAll() {
        return eventLogService.findAll()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @PostMapping(name = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<EventLogDto> save(@ApiParam(value = "Объект EventLogDto", example = "{\"id\":1,\"val\":\"123\"}") @RequestBody EventLogDto userEventDto) {
//        return eventLogService.save(userEventDto)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @DeleteMapping("/{id:[\\d]+}")
    public ResponseEntity<EventLogDto> deleteById(@ApiParam(value = "Индентификатор объекта EventLogDto", example = "1") @PathVariable Long id) {
        return eventLogService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/")
    public ResponseEntity<List<EventLogDto>> deleteAll() {
        return eventLogService.deleteAll()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
