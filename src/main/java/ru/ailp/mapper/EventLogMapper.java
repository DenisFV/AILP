package ru.ailp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ailp.dto.EventLogDto;
import ru.ailp.dto.helper.EventLogEventHelper;
import ru.ailp.entity.EventLogEntity;
import ru.ailp.mapper.abstr.CommonMapper;

@Mapper
public interface EventLogMapper extends CommonMapper<EventLogEntity, EventLogDto> {

    @Mapping(target = "eventId", source = "eventLogEventHelper.eventDto.id")
    EventLogDto eventLogEventHelperToEventLogDto(EventLogEventHelper eventLogEventHelper);
}