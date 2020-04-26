package ru.ailp.config;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ru.ailp.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @NonNull UserService userService;
    @NonNull CustomLogoutSuccessHandler logoutSuccessHandler;
    @NonNull CustomLoginSuccessHandler loginSuccessHandler;

    @Autowired
    public WebSecurityConfig(UserService userService, CustomLogoutSuccessHandler logoutSuccessHandler,
                             CustomLoginSuccessHandler loginSuccessHandler) {
        this.userService = userService;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @SneakyThrows
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("1").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/**")
                .permitAll()
                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .successHandler(loginSuccessHandler)
                .permitAll()

                .and()
                .rememberMe()

                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
