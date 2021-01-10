package me.study.springsecurityjwtdemo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.study.springsecurityjwtdemo.domain.SocialProviders;

@Data
public class SocialLoginDto {

    @JsonProperty("provider")
    private SocialProviders providers;

    @JsonProperty("token")
    private String token;

    public SocialLoginDto() {
    }

    public SocialLoginDto(SocialProviders providers, String token) {
        this.providers = providers;
        this.token = token;
    }
}
