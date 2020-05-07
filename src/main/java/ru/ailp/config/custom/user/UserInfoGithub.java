package ru.ailp.config.custom.user;

import ru.ailp.config.custom.user.factory.UserInfoFactory;
import ru.ailp.dto.UserDto;
import ru.ailp.entity.enums.AuthProvider;

import java.time.LocalDateTime;

public class UserInfoGithub extends UserInfoFactory {

    public static UserDto getUser() {
        return UserDto.builder()
                .providerId(getStringAttr("id"))
                .provider(AuthProvider.github)
                .username(getUserNameAttr())
                .firstName(getStringAttr("name"))
                .email(getStringAttr("email"))
                .imageUrl(getStringAttr("avatar_url"))
                .createDate(LocalDateTime.now())
                .isActive(true)
                .build();
    }

    private static String getUserNameAttr() {
        return (getStringAttr("login")
                + "_" + getStringAttr("id")
        ).trim();
    }
}
