package ru.ailp.dto.helper;

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
@ApiModel(value = "EventLogHelper", description = "EventLogHelper")
public class EventLogHelper extends AbstractEntity {

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
