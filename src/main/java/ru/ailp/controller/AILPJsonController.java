package ru.ailp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.util.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ailp.dto.UserEventDto;
import ru.ailp.entity.UserEventEntity;
import ru.ailp.service.UserEventService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ailp")
@Api(tags = {"AILPJsonController"}, description = "Ресты AILP", produces = MediaType.APPLICATION_JSON_VALUE)
public class AILPJsonController {

    private static final Logger logger = LoggerFactory.getLogger(AILPJsonController.class);
    private final UserEventService userEventService;

    @Autowired
    public AILPJsonController(UserEventService userEventService) {
        this.userEventService = userEventService;
    }



    @PostMapping(value = "/"
            , produces = {MediaType.APPLICATION_JSON_VALUE}
            )
    @ApiOperation(value = "main", notes = "main")
    public ResponseEntity<UserEventDto> saves(HttpServletRequest request
//            , @RequestBody UserEventDto userEvent
    ) {
        UserEventDto userEventDto = new UserEventDto();
        userEventDto.setPageId(Long.parseLong(request.getParameter("page"))); // request.getParameter("page");
        userEventDto.setEventId(101L); // = request.getParameter("type"); + request.getParameter("name");
        userEventDto.setUserId(6L);
        userEventDto.setEventDate(LocalDateTime.parse(request.getParameter("eventDate")));

        return userEventService.save(userEventDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




    /**
     * Достает из кеша указанный по полю 'id' объект (если его там нет, то загружает его из БД)
     *
     * @param id - индентификатор объекта
     * @return объект UserEventDto
     */
    @GetMapping("/{id:[\\d]+}")
    @ApiOperation(value = "Поиск объекта по id", notes = "Поиск объекта по id")
    public ResponseEntity<UserEventDto> findById(@ApiParam(value = "Индентификатор объекта UserEventDto", example = "1") @PathVariable Long id) {
        logger.info("Поиск объекта по id");
        return userEventService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Достает все объекты из БД
     *
     * @return 200 / 404
     */
    @GetMapping("/")
    @ApiOperation(value = "Поиск всех объектов", notes = "Поиск всех объектов")
    public ResponseEntity<List<UserEventDto>> findAll() {
        logger.info("Поиск всех объектов");
        return userEventService.findAll()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    /**
//     * Сохраняет / Обновляет переданный в теле объект
//     *
//     * @param userEventDto - объект для сохранения / обновления
//     * @return 200 / 404
//     */
//    @PostMapping(name = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ApiOperation(value = "Сохранение / Обновление объекта", notes = "Сохранение / Обновление объекта")
//    public ResponseEntity<UserEventDto> save(@ApiParam(value = "Объект UserEventDto", example = "{\"id\":1,\"val\":\"123\"}") @RequestBody UserEventDto userEventDto) {
//        logger.info("Сохранение / Обновление объекта: {}", userEventDto);
//        return userEventService.save(userEventDto)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    /**
     * Удаляет из БД объект по 'id'
     *
     * @param id - индентификатор объекта для удаления
     * @return 200 / 404
     */
    @DeleteMapping("/{id:[\\d]+}")
    @ApiOperation(value = "Удаление объекта по id", notes = "Удаление объекта по id")
    public ResponseEntity<UserEventDto> deleteById(@ApiParam(value = "Индентификатор объекта UserEventDto", example = "1") @PathVariable Long id) {
        logger.info("Удаление объекта по id: {}", id);
        return userEventService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Удаляет все записи из БД
     *
     * @return 200 / 404
     */
    @DeleteMapping("/")
    @ApiOperation(value = "Удаление всех объектов", notes = "Удаление всех объектов")
    public ResponseEntity<List<UserEventDto>> deleteAll() {
        logger.info("Удаление всех объектов");
        return userEventService.deleteAll()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
