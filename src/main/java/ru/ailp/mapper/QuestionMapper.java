package ru.ailp.mapper;

import org.mapstruct.Mapper;
import ru.ailp.dto.QuestionDto;
import ru.ailp.dto.helper.QuestionAnswerHelper;
import ru.ailp.entity.QuestionEntity;
import ru.ailp.mapper.abstr.CommonMapper;

import java.util.List;

@Mapper
public interface QuestionMapper extends CommonMapper<QuestionEntity, QuestionDto> {

    List<QuestionAnswerHelper> questionDtoListToQuestionAnswerHelperList(List<QuestionDto> questionDtoList);
}