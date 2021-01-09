package me.study.springsecurityjwtdemo.security.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.study.springsecurityjwtdemo.dtos.TokenDto;
import me.study.springsecurityjwtdemo.security.AccountContext;
import me.study.springsecurityjwtdemo.security.JwtFactory;
import me.study.springsecurityjwtdemo.security.tokens.PostAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Component
public class FormLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    /*
    PostAuthorizationToken이 FormLoginAuthenticationProvider에서 넘어오면
    넘어온 토큰을 가지고 FormLoginFilter의 successfulAuthentication를 호출한다.
    호출할때 자신이 가지고 있는 http context안에서 request와 response를 매핑해서 authenticationResult와 함께 보낸다.
     */

    @Autowired
    private JwtFactory factory;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
        PostAuthorizationToken token = (PostAuthorizationToken) auth;

        AccountContext context = (AccountContext) token.getPrincipal();

        String tokenString = factory.generateToken(context);

        processResponse(res, writeDto(tokenString));
    }

    private TokenDto writeDto(String token) {
        return new TokenDto(token);
    }

    private void processResponse(HttpServletResponse res, TokenDto dto) throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setStatus(HttpStatus.OK.value());
        res.getWriter().write(objectMapper.writeValueAsString(dto));
    }
}
