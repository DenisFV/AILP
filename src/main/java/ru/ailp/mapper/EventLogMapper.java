package ru.ailp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ailp.dto.EventLogDto;
import ru.ailp.entity.EventLogEntity;
import ru.ailp.entity.helper.EventLogHelper;
import ru.ailp.mapper.abstr.CommonMapper;

@Mapper
public interface EventLogMapper extends CommonMapper<EventLogEntity, EventLogDto> {

    @Mapping(target = "eventId", source = "eventLogHelper.eventEntity.id")
    EventLogDto eventLogHelperToEventLogDto(EventLogHelper eventLogHelper);

    @Mapping(target = "eventEntity", expression = "java(new EventEntity(eventLogHelper.getId()))")
    EventLogHelper eventLogDtoToEventLogHelper(EventLogDto eventLogDto);
}