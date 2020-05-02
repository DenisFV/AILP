package ru.ailp.mapper;

import org.mapstruct.Mapper;
import ru.ailp.dto.AnswerDto;
import ru.ailp.entity.AnswerEntity;
import ru.ailp.mapper.abstr.CommonMapper;

@Mapper
public interface AnswerMapper extends CommonMapper<AnswerEntity, AnswerDto> {
}