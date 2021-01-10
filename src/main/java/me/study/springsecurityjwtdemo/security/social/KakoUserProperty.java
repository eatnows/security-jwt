package me.study.springsecurityjwtdemo.security.social;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Data
public class KakoUserProperty implements SocialUserProperty {
    /*
    카카오에서 넘겨주는 json 값과 변수와 매핑이 되게 JsonProperty에 값을 정해준다.
    JsonProperty의 값에 카카오가 보내주는 json의 키값을 적어주면 된다.
     */

    @JsonProperty("kakao_account.email")
    private String userEmail;

    @JsonProperty("kakao_account.is_email_verified")
    private Boolean verified;

    @JsonProperty("id")
    private Long userUniqueId;

    @JsonProperty("properties")
    private Map<String, String> userProperties;


    @Override
    public String getUserId() {
        return userUniqueId.toString();
    }

    @Override
    public String getUserNickname() {
        return userProperties.get("nickname").toString();
    }

    @Override
    public String getProfileHref() {
        return userProperties.get("profile").toString();
    }

    @Override
    public String getEmail() {
        return userEmail;
    }
}
