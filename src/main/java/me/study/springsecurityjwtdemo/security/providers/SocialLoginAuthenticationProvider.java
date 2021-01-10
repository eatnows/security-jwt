package me.study.springsecurityjwtdemo.security.providers;

import me.study.springsecurityjwtdemo.domain.Account;
import me.study.springsecurityjwtdemo.domain.AccountRepository;
import me.study.springsecurityjwtdemo.domain.SocialProviders;
import me.study.springsecurityjwtdemo.domain.UserRole;
import me.study.springsecurityjwtdemo.dtos.SocialLoginDto;
import me.study.springsecurityjwtdemo.security.AccountContext;
import me.study.springsecurityjwtdemo.security.service.specification.SocialFetchService;
import me.study.springsecurityjwtdemo.security.social.SocialUserProperty;
import me.study.springsecurityjwtdemo.security.tokens.PostAuthorizationToken;
import me.study.springsecurityjwtdemo.security.tokens.SocialPreAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SocialLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SocialFetchService service;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SocialPreAuthorizationToken token = (SocialPreAuthorizationToken) authentication;
        SocialLoginDto dto = token.getDto();

        return PostAuthorizationToken.getTokenFromAccountContext(AccountContext.fromAccountModel(getAccount(dto)));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SocialPreAuthorizationToken.class.isAssignableFrom(aClass);
    }

    private Account getAccount(SocialLoginDto dto) {
        SocialUserProperty property = service.getSocialUserInfo(dto);

        String userId = property.getUserId();
        SocialProviders provider = dto.getProviders();

        // DB에 유저가 있으면 가져오고 없으면 새로 추가
        return accountRepository.findBySocialIdAndProvider(Long.valueOf(userId), provider)
                .orElseGet(() -> {
                    return accountRepository.save(
                            new Account(null, property.getUserNickname(), "SOCIAL_USER", String.valueOf(UUID.randomUUID().getMostSignificantBits()), UserRole.USER, Long.valueOf(property.getUserId()), provider, property.getProfileHref())
                    );
                });
    }
}
