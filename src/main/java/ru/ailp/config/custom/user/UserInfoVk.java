package ru.ailp.config.custom.user;

import ru.ailp.config.custom.user.factory.UserInfoFactory;
import ru.ailp.dto.UserDto;
import ru.ailp.entity.enums.AuthProvider;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class UserInfoVk extends UserInfoFactory {

    public static UserDto getUser() {
        return UserDto.builder()
                .providerId(getStringAttr("id"))
                .provider(AuthProvider.vk)
                .username(getUserNameAttr())
                .firstName(getStringAttr("first_name"))
                .lastName(getStringAttr("last_name"))
                .email(getStringAttr("email"))
                .country(getTitleAttr("country"))
                .city(getTitleAttr("city"))
                .imageUrl(getStringAttr("photo_max"))
                .createDate(LocalDateTime.now())
                .isActive(true)
                .build();
    }

    private static String getTitleAttr(String attr) {
        return Objects.nonNull(attrs.get(attr))
                ? getStringAttr((Map) attrs.get(attr), "title")
                : null;
    }

    private static String getUserNameAttr() {
        return (getStringAttr("first_name")
                + "_" + getStringAttr("last_name")
                + "_" + getStringAttr("id")
        ).trim();
    }
}
