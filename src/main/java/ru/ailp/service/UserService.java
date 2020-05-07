package ru.ailp.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ailp.dto.UserDto;
import ru.ailp.entity.UserEntity;
import ru.ailp.mapper.UserMapper;
import ru.ailp.repo.UserRepo;
import ru.ailp.service.abstr.AbstractService;

import java.util.Optional;

@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service("userService")
public class UserService extends AbstractService<UserEntity, UserDto, UserRepo, UserMapper> implements UserDetailsService {

    @NonNull
    static UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @NonNull UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        super(userRepo, userMapper);
        this.userRepo = userRepo;
    }

    public UserDto buildUserDto(UserDto userDto, Long id) {
        return UserDto.builder()
                .id(id)
                .planId(userDto.getPlanId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .imageUrl(userDto.getImageUrl())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .country(userDto.getCountry())
                .city(userDto.getCity())
                .createDate(userDto.getCreateDate())
                .isActive(userDto.getIsActive())
                .providerId(userDto.getProviderId())
                .provider(userDto.getProvider())
                .attributes(userDto.getAttributes())
                .build();
    }

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .map(userMapper::entityToDto)
                .orElseGet(() -> {
                    log.error("Пользователя с username: {} не сущетсвует", username);
                    return null;
                });
    }

    public static UserDto getAuthenticationUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(e -> ((UserDto) e))
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не определен"));
    }
}
