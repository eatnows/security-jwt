package me.study.springsecurityjwtdemo.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.study.springsecurityjwtdemo.dtos.FormLoginDto;
import me.study.springsecurityjwtdemo.security.tokens.PreAuthorizationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
로그인을 할 때 로그인을 걸러주는 필터
필터는 컴포넌트로 만들게 아니다.
 */
public class FormLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;

    public FormLoginFilter(String defaultUrl, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        super(defaultUrl);

        this.authenticationSuccessHandler = successHandler;
        this.authenticationFailureHandler = failureHandler;
    }

    protected FormLoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    // attemptAuthentication으로 인증을 시도함 -> FormLoginAuthenticationProvider의 authenticate를 실행하여 인증하는데
    // 성공을 하면 인증객체가 나올 것이고, 실패를 하면 Exception
    // 성공했을때 실패했을때에 대한 각각의 로직을 작성해주면 됨
    // JWT를 만들고 토큰을 HTTP response로 내려주는것 까지 해야함

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {

        FormLoginDto dto = new ObjectMapper().readValue(req.getReader(), FormLoginDto.class);

        PreAuthorizationToken token = new PreAuthorizationToken(dto);

        // PreAuthorizationToken에 맞는 토큰을 AuthenticationManager가 알아서 검색함(브루스 포스로 검색)
        return super.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
    }

    // ID가 틀리거나 비밀번호가 안맞는 등 다양한 이유로 인증에 실패하면 unsuccessfulAuthentication을 통해 구현하면됨.
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        AuthenticationFailureHandler handler = (req, res, exception) -> {
            Logger log = LoggerFactory.getLogger("authentication_failure");

            log.error(exception.getMessage());
        };

        handler.onAuthenticationFailure(request, response, failed);
    }
}
