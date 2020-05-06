package ru.ailp.dto.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.ailp.dto.AnswerDto;
import ru.ailp.entity.abstr.AbstractEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "QuestionAnswerHelper", description = "QuestionAnswerHelper")
public class QuestionAnswerHelper extends AbstractEntity {

    @ApiModelProperty(value = "Индентификатор объекта", position = 1, example = "1")
    @JsonProperty("question_id")
    Long id;
    Long testId;
    String answerOption;
    String question;
    String comment;
    List<AnswerDto> answerList;
}
