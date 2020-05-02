package ru.ailp.mapper;

import org.mapstruct.Mapper;
import ru.ailp.dto.LessonTestDto;
import ru.ailp.entity.LessonTestEntity;
import ru.ailp.mapper.abstr.CommonMapper;

@Mapper
public interface LessonTestMapper extends CommonMapper<LessonTestEntity, LessonTestDto> {
}