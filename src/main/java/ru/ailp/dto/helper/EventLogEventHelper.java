package ru.ailp.dto.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.ailp.dto.EventDto;
import ru.ailp.entity.EventEntity;
import ru.ailp.entity.abstr.AbstractEntity;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "EventLogEventHelper", description = "EventLogEventHelper")
public class EventLogEventHelper extends AbstractEntity {

    @ApiModelProperty(value = "Индентификатор объекта", position = 1, example = "1")
    @JsonProperty("event_log_id")
    Long id;
    Long userId;
    EventDto eventDto;
    LocalDateTime eventDate;
    Long pageId;
    Long lessonId;
}
