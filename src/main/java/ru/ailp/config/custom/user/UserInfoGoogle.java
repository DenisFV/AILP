package ru.ailp.config.custom.user;

import ru.ailp.config.custom.user.factory.UserInfoFactory;
import ru.ailp.dto.UserDto;
import ru.ailp.entity.enums.AuthProvider;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserInfoGoogle extends UserInfoFactory {

    public static UserDto getUser() {
        return UserDto.builder()
                .providerId(getStringAttr("sub"))
                .provider(AuthProvider.google)
                .username(getUserNameAttr())
                .firstName(getFirstNameAttr())
                .lastName(getLastNameAttr())
                .email(getStringAttr("email"))
                .imageUrl(getStringAttr("picture"))
                .createDate(LocalDateTime.now())
                .isActive(true)
                .build();
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
                + "_" + getStringAttr("sub")
        ).trim();
    }
}