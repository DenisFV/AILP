package ru.ailp.mapper;

import org.mapstruct.Mapper;
import ru.ailp.dto.EventDto;
import ru.ailp.entity.EventEntity;
import ru.ailp.mapper.abstr.CommonMapper;

@Mapper
public interface EventMapper extends CommonMapper<EventEntity, EventDto> {
}