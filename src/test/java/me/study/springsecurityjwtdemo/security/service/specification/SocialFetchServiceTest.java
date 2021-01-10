package me.study.springsecurityjwtdemo.security.service.specification;

import lombok.extern.slf4j.Slf4j;
import me.study.springsecurityjwtdemo.domain.SocialProviders;
import me.study.springsecurityjwtdemo.dtos.SocialLoginDto;
import me.study.springsecurityjwtdemo.security.service.impl.SocialFetchServiceProd;
import me.study.springsecurityjwtdemo.security.social.SocialUserProperty;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@Slf4j
public class SocialFetchServiceTest {

    private SocialFetchServiceProd prod = new SocialFetchServiceProd();
    private SocialLoginDto dto;

    @Before
    public void setUp() {
        this.dto = new SocialLoginDto(SocialProviders.KAKAO, System.getenv("kakao_token"));
    }

    @Test
    public void restTemplate_Pactice1() {
        log.debug(new RestTemplate().getForObject("http://www.naver.com", String.class));
    }

    @Test
    public void service_fetchSocialInfo() {
        SocialUserProperty property = prod.getSocialUserInfo(this.dto);

        assertThat(property.getEmail(), is(System.getenv("kakao_email")));
    }
}