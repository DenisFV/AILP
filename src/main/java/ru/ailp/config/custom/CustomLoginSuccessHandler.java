package ru.ailp.config.custom;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.ailp.util.HttpReqRespUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @SneakyThrows
    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, Authentication auth) {
        onAuthenticationSuccess(req, resp, auth);
    }

    @SneakyThrows
    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) {

        log.info("Login : {}, user_ip : {}", req.getRequestURL(), HttpReqRespUtils.getRemoteIP(req));

        resp.sendRedirect("/");
    }
}
