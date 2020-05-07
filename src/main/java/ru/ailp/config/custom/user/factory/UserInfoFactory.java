package ru.ailp.config.custom.user.factory;

import org.springframework.util.StringUtils;
import ru.ailp.config.custom.user.UserInfoFacebook;
import ru.ailp.config.custom.user.UserInfoGithub;
import ru.ailp.config.custom.user.UserInfoGoogle;
import ru.ailp.config.custom.user.UserInfoVk;
import ru.ailp.dto.UserDto;
import ru.ailp.entity.enums.AuthProvider;

import javax.ws.rs.NotFoundException;
import java.util.Map;

public class UserInfoFactory {

    protected static Map<String, Object> attrs;

    public static UserDto getUserInfo(String registrationId, Map<String, Object> attributes) {
        attrs = attributes;
        if (registrationId.equalsIgnoreCase(AuthProvider.google.name())) {
            return UserInfoGoogle.getUser();
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.name())) {
            return UserInfoFacebook.getUser();
        } else if (registrationId.equalsIgnoreCase(AuthProvider.github.name())) {
            return UserInfoGithub.getUser();
        } else if (registrationId.equalsIgnoreCase(AuthProvider.vk.name())) {
            return UserInfoVk.getUser();
        } else {
            throw new NotFoundException("Аутентификация через " + registrationId + " не поддерживается");
        }
    }

    protected static String getStringAttr(String attr) {
        return StringUtils.isEmpty(attrs.get(attr)) ? null : String.valueOf(attrs.get(attr));
    }

    protected static String getStringAttr(Map<String, Object> attributes, String attr) {
        return StringUtils.isEmpty(attributes.get(attr)) ? null : String.valueOf(attributes.get(attr));
    }
}