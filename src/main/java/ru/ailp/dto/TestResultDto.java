package ru.ailp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.ailp.entity.abstr.AbstractEntity;

@EqualsAndHashCode(callSuper = false)
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "TestResultDto", description = "TestResultDto")
public class TestResultDto extends AbstractEntity {

    @ApiModelProperty(value = "Индентификатор объекта", position = 1, example = "1")
    @JsonProperty("test_result_id")
    Long id;
    Long testId;
    Long questionId;
    Long userId;
    Long answerId;
    String result;
    String comment;
}
