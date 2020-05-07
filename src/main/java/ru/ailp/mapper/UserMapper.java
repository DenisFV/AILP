package ru.ailp.mapper;

import org.mapstruct.Mapper;
import ru.ailp.dto.UserDto;
import ru.ailp.entity.UserEntity;
import ru.ailp.mapper.abstr.CommonMapper;

@Mapper
public interface UserMapper extends CommonMapper<UserEntity, UserDto> {
}