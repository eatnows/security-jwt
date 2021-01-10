package me.study.springsecurityjwtdemo.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.study.springsecurityjwtdemo.dtos.SocialLoginDto;
import me.study.springsecurityjwtdemo.security.tokens.PreAuthorizationToken;
import me.study.springsecurityjwtdemo.security.tokens.SocialPreAuthorizationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SocialLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationSuccessHandler successHandler;

    protected SocialLoginFilter(String defaultFilterProcessUrl) {
        super(defaultFilterProcessUrl);
    }

    public SocialLoginFilter(String url, AuthenticationSuccessHandler handler) {
        super(url);
        this.successHandler = handler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {

        ObjectMapper objectMapper = new ObjectMapper();

        SocialLoginDto dto = objectMapper.readValue(req.getReader(), SocialLoginDto.class);
        return super.getAuthenticationManager().authenticate(new SocialPreAuthorizationToken(dto));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.successHandler.onAuthenticationSuccess(request, response, authResult);
    }
}
