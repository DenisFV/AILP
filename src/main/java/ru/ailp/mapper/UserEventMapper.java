package ru.ailp.mapper;

import org.mapstruct.Mapper;
import ru.ailp.dto.UserEventDto;
import ru.ailp.entity.UserEventEntity;

import java.util.List;

@Mapper
public interface UserEventMapper {
    List<UserEventDto> userEventEntityListToUserEventDtoList(List<UserEventEntity> userEventEntityList);

    UserEventDto userEventEntityToUserEventDto(UserEventEntity userEventEntity);

    UserEventEntity userEventDtoToUserEventEntity(UserEventDto userEventDto);
}