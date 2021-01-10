package me.study.springsecurityjwtdemo.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import me.study.springsecurityjwtdemo.security.social.KakoUserProperty;
import me.study.springsecurityjwtdemo.security.social.SocialUserProperty;

@Getter
public enum SocialProviders {

    KAKAO("https://kapi.kakao.com/v2/user/me", KakoUserProperty.class);

    private String userinfoEndpoint;
    private Class<? extends SocialUserProperty> propertyMetaclass;

    SocialProviders(String userinfoEndpoint, Class<? extends SocialUserProperty> propertyMetaclass) {
        this.userinfoEndpoint = userinfoEndpoint;
        this.propertyMetaclass = propertyMetaclass;
    }

    @JsonValue
    public String getProviderName() {
        return this.name();
    }
}
