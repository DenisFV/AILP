package ru.ailp.mapper;

import org.mapstruct.Mapper;
import ru.ailp.dto.EventLogDto;
import ru.ailp.entity.EventLogEntity;

import java.util.List;

@Mapper
public interface EventLogMapper {
    List<EventLogDto> eventLogEntityListToEventLogDtoList(List<EventLogEntity> userEventEntityList);

    EventLogDto eventLogEntityToEventLogDto(EventLogEntity eventLogEntity);

    EventLogEntity eventLogDtoToEventLogEntity(EventLogDto eventLogDto);
}