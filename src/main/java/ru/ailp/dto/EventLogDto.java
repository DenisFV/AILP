package ru.ailp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "EventLogDto", description = "UserEvent")
public class EventLogDto implements Serializable {

    @ApiModelProperty(value = "Индентификатор объекта", position = 1, example = "1")
    private Long eventLogId;
    private Long userId;
    private Long eventId;
    private LocalDateTime eventDate;
    private Long pageId;
    private Long lessonId;
}
