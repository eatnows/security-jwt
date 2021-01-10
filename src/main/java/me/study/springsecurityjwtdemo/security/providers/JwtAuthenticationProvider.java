package me.study.springsecurityjwtdemo.security.providers;

import me.study.springsecurityjwtdemo.security.AccountContext;
import me.study.springsecurityjwtdemo.security.JwtDecoder;
import me.study.springsecurityjwtdemo.security.tokens.JwtPreProcessingToken;
import me.study.springsecurityjwtdemo.security.tokens.PostAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtDecoder jwtDecoder;

    /*
    인증 로직
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String token = (String) authentication.getPrincipal();
        AccountContext context = jwtDecoder.decodeJwt(token);

        return PostAuthorizationToken.getTokenFromAccountContext(context);
    }

    /*
     Jwt 인증전 토큰을 서포트해주면된다. 즉 JwtPreProcessingToken
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return JwtPreProcessingToken.class.isAssignableFrom(aClass);
    }
}
