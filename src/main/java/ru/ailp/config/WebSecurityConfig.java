package ru.ailp.config;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.ailp.config.custom.*;
import ru.ailp.service.UserService;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @NonNull UserService userService;
    @NonNull CustomLogoutSuccessHandler logoutSuccessHandler;
    @NonNull CustomLoginSuccessHandler loginSuccessHandler;
    @NonNull CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    public WebSecurityConfig(UserService userService, CustomLogoutSuccessHandler logoutSuccessHandler, CustomLoginSuccessHandler loginSuccessHandler, @NonNull CustomOAuth2UserService customOAuth2UserService) {
        this.userService = userService;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.loginSuccessHandler = loginSuccessHandler;
        this.customOAuth2UserService = customOAuth2UserService;
    }

//    @SneakyThrows
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password("1").roles("ADMIN");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/**")
                .permitAll()
                .anyRequest()
                .authenticated()

                .and()
                .oauth2Login()
                .successHandler(loginSuccessHandler)
                .permitAll()

                .tokenEndpoint()
                .accessTokenResponseClient(accessTokenResponseClient())
                .and()

                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()

//                .and()
//                .rememberMe()

                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        DefaultAuthorizationCodeTokenResponseClient accessTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        accessTokenResponseClient.setRequestEntityConverter(new CustomRequestEntityConverter());

        OAuth2AccessTokenResponseHttpMessageConverter tokenResponseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
        tokenResponseHttpMessageConverter.setTokenResponseConverter(new CustomTokenResponseConverter());
        RestTemplate restTemplate = new RestTemplate(Arrays.asList(new FormHttpMessageConverter(), tokenResponseHttpMessageConverter));
        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());

        accessTokenResponseClient.setRestOperations(restTemplate);
        return accessTokenResponseClient;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
