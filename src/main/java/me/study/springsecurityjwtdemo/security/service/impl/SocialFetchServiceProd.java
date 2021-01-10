package me.study.springsecurityjwtdemo.security.service.impl;

import me.study.springsecurityjwtdemo.domain.SocialProviders;
import me.study.springsecurityjwtdemo.dtos.SocialLoginDto;
import me.study.springsecurityjwtdemo.security.service.specification.SocialFetchService;
import me.study.springsecurityjwtdemo.security.social.SocialUserProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class SocialFetchServiceProd implements SocialFetchService {

    private static final String HEADER_PREFIX = "Bearer ";

    @Override
    public SocialUserProperty getSocialUserInfo(SocialLoginDto dto) {
        SocialProviders provider = dto.getProviders();
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>("parameter", generateHeader(dto.getToken()));

        return restTemplate.exchange(provider.getUserinfoEndpoint(), HttpMethod.GET, entity, provider.getPropertyMetaclass()).getBody();
    }

    private HttpHeaders generateHeader(String token) {
        HttpHeaders header = new HttpHeaders();

        header.add("Authorization", generateHeaderContext(token));
        return header;
    }

    private String generateHeaderContext(String token) {
        StringBuilder sb = new StringBuilder();

        sb.append(HEADER_PREFIX);
        sb.append(token);

        return sb.toString();
    }
}
