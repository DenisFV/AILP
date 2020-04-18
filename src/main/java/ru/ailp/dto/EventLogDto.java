package ru.ailp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.ailp.entity.abstr.AbstractEntity;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "EventLogDto", description = "EventLogDto")
public class EventLogDto extends AbstractEntity {

    @ApiModelProperty(value = "Индентификатор объекта", position = 1, example = "1")
    @JsonProperty("event_log_id")
    Long id;
    Long userId;
    Long eventId;
    LocalDateTime eventDate;
    Long pageId;
    Long lessonId;
}
