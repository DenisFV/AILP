package ru.ailp.config.custom;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CustomTokenResponseConverter implements Converter<Map<String, String>, OAuth2AccessTokenResponse> {

    @Override
    public OAuth2AccessTokenResponse convert(Map<String, String> tokenResponseParameters) {
        String accessToken = tokenResponseParameters.get(OAuth2ParameterNames.ACCESS_TOKEN);
        OAuth2AccessToken.TokenType accessTokenType = OAuth2AccessToken.TokenType.BEARER;
        String expiresIn = tokenResponseParameters.get(OAuth2ParameterNames.EXPIRES_IN);
        Set<String> scopes = Collections.singleton(tokenResponseParameters.get(OAuth2ParameterNames.SCOPE));
        Map<String, Object> additionalParameters = Map.copyOf(tokenResponseParameters);

        if(Objects.nonNull(expiresIn)){
            return OAuth2AccessTokenResponse.withToken(accessToken)
                    .tokenType(accessTokenType)
                    .expiresIn(Long.valueOf(expiresIn))
                    .scopes(scopes)
                    .additionalParameters(additionalParameters)
                    .build();
        } else {
            return OAuth2AccessTokenResponse.withToken(accessToken)
                    .tokenType(accessTokenType)
                    .scopes(scopes)
                    .build();
        }
    }
}
