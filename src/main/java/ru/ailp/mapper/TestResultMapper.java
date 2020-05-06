package ru.ailp.mapper;

import org.mapstruct.Mapper;
import ru.ailp.dto.TestResultDto;
import ru.ailp.entity.TestResultEntity;
import ru.ailp.mapper.abstr.CommonMapper;

@Mapper
public interface TestResultMapper extends CommonMapper<TestResultEntity, TestResultDto> {
}