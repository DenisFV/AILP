package ru.ailp.config.custom;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import ru.ailp.util.HttpReqRespUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    @SneakyThrows
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exc) {

        log.error("Login : {}, user_ip : {}, error : {}", req.getRequestURL(), HttpReqRespUtils.getRemoteIP(req), exc.getMessage());

        resp.sendRedirect("/");
    }
}