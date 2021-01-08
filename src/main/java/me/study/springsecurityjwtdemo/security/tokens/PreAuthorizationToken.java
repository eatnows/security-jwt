package me.study.springsecurityjwtdemo.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    // UsernamePasswordAuthenticationToken가 가지고 있는 메소드 중
    // 매개변수가 2개짜리인 것은 인증전 객체를 의미하는 메서드.
    // 3개짜리는 인증이 된 객체
    // 2개짜리 메서드를 따로 생성자로 만들어 준것 super(~);
    public PreAuthorizationToken(String username, String password) {
        super(username, password);
    }
    
    // getPrincipal()을 그냥 해도 되지만
    // 명시적으로 무엇을 가져오는지 더 확실하게 하기위해 메서드를 만듦
    public String getUsername() {
        return (String) super.getPrincipal();
    }

    public String getUserPassword() {
        return (String) super.getCredentials();
    }

}
