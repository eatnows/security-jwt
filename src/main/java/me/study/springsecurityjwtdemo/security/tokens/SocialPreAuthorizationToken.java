package me.study.springsecurityjwtdemo.security.tokens;

import me.study.springsecurityjwtdemo.dtos.SocialLoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class SocialPreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    public SocialPreAuthorizationToken(SocialLoginDto dto) {
        super(dto.getProviders(), dto);
    }

    public SocialLoginDto getDto() {
        return (SocialLoginDto) super.getCredentials();
    }
}
