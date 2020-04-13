package ru.ailp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.json.JSONObject;
import ru.ailp.entity.EventEntity;
import ru.ailp.entity.abstr.AbstractEntity;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "EventLogDto", description = "EventLogDto")
public final class EventDto extends AbstractEntity {

    @ApiModelProperty(value = "Индентификатор объекта", position = 1, example = "1")
    Long id;
    Long userId;
    EventEntity eventEntity;
    LocalDateTime eventDate;
    Long pageId;
    Long lessonId;

    @Override
    public String toString() {
        return new JSONObject(this).toString(4);
    }
}
