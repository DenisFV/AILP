package ru.ailp.config.custom;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import ru.ailp.config.custom.user.factory.UserInfoFactory;
import ru.ailp.dto.UserDto;
import ru.ailp.service.UserService;

import javax.ws.rs.NotFoundException;
import java.util.*;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @NonNull UserService userService;

    @Autowired
    public CustomOAuth2UserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = oAuth2UserRequest.getClientRegistration().getRegistrationId().equals("vk")
                ? loadVkUser(oAuth2UserRequest)
                : super.loadUser(oAuth2UserRequest);

        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        UserDto userInfo = UserInfoFactory.getUserInfo(
                oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes());

        if (StringUtils.isEmpty(userInfo.getUsername())) {
            throw new NotFoundException("Username не указан");
        }

        UserDto userDto = Optional.ofNullable(userService.loadUserByUsername(userInfo.getUsername()))
                .map(e->userService.buildUserDto(userInfo, e.getId()))
                .orElse(userInfo);

        return userService.save(userDto)
                .orElseThrow(() -> new RuntimeException("Ошибка при сохранении пользователя"));
    }

    private OAuth2User loadVkUser(OAuth2UserRequest oAuth2UserRequest) {
        String uri = oAuth2UserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri()
                .replace("{access_token}", oAuth2UserRequest.getAccessToken().getTokenValue());

        Optional<Map<String, ArrayList<Map<String, Object>>>> body = Optional.ofNullable(
                (Map<String, ArrayList<Map<String, Object>>>) new RestTemplate().getForEntity(uri, Object.class).getBody()
        );

        Map<String, Object> userAttributes = body
                .map(e -> e.get("response"))
                .map(e -> e.get(0))
                .orElseGet(() -> {
                    throw new RuntimeException("getBody is null");
                });

        userAttributes.put("id", userAttributes.get("id"));

        Set<GrantedAuthority> authorities = Collections.singleton(new OAuth2UserAuthority(userAttributes));
        return new DefaultOAuth2User(authorities, userAttributes, "id");
    }
}
