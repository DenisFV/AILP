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
@ApiModel(value = "AnswerDto", description = "AnswerDto")
public class AnswerDto extends AbstractEntity {

    @ApiModelProperty(value = "Индентификатор объекта", position = 1, example = "1")
    @JsonProperty("answer_id")
    Long id;
    Long questionId;
    String answer;
    String comment;
}
