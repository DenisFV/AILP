package ru.ailp.config.custom.user;

import ru.ailp.config.custom.user.factory.UserInfoFactory;
import ru.ailp.dto.UserDto;
import ru.ailp.entity.enums.AuthProvider;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class UserInfoFacebook extends UserInfoFactory {

    public static UserDto getUser() {
        return UserDto.builder()
                .providerId(getStringAttr("id"))
                .provider(AuthProvider.facebook)
                .username(getUserNameAttr())
                .firstName(getFirstNameAttr())
                .lastName(getLastNameAttr())
                .email(getStringAttr("email"))
                .imageUrl(getImageUrl())
                .createDate(LocalDateTime.now())
                .isActive(true)
                .build();
    }

    private static String getImageUrl() {
        return Objects.nonNull(attrs.get("picture"))
                ? Objects.nonNull(((Map) attrs.get("picture")).get("data"))
                ? getStringAttr((Map<String, Object>) ((Map) attrs.get("picture")).get("data"), "url") : null
                : null;
    }

    private static String getFirstNameAttr() {
        return Objects.nonNull(getStringAttr("name"))
                ? getStringAttr("name").split(" ")[0]
                : null;
    }

    private static String getLastNameAttr() {
        return Objects.nonNull(getStringAttr("name"))
                ? getStringAttr("name").split(" ")[1]
                : null;
    }

    private static String getUserNameAttr() {
        return (getFirstNameAttr()
                + "_" + getLastNameAttr()
                + "_" + getStringAttr("id")
        ).trim();
    }
}