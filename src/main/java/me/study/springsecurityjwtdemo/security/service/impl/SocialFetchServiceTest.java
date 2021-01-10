package me.study.springsecurityjwtdemo.security.service.impl;

import me.study.springsecurityjwtdemo.dtos.SocialLoginDto;
import me.study.springsecurityjwtdemo.security.service.specification.SocialFetchService;
import me.study.springsecurityjwtdemo.security.social.SocialUserProperty;
import org.springframework.stereotype.Service;

@Service
public class SocialFetchServiceTest implements SocialFetchService {
    @Override
    public SocialUserProperty getSocialUserInfo(SocialLoginDto dto) {
        return null;
    }
}
