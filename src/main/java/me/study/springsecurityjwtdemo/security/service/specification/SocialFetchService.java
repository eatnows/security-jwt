package me.study.springsecurityjwtdemo.security.service.specification;

import me.study.springsecurityjwtdemo.dtos.SocialLoginDto;
import me.study.springsecurityjwtdemo.security.social.SocialUserProperty;

public interface SocialFetchService {

    SocialUserProperty getSocialUserInfo(SocialLoginDto dto);
}
