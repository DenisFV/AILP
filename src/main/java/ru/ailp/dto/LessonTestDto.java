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
@ApiModel(value = "LessonTestDto", description = "LessonTestDto")
public class LessonTestDto extends AbstractEntity {

    @ApiModelProperty(value = "Индентификатор объекта", position = 1, example = "1")
    @JsonProperty("lesson_test_id")
    Long id;
    Long lessonId;
    Long pageId;
    Long testId;
    Long testTypeId;
    String testName;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
