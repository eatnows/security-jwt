package me.study.springsecurityjwtdemo.security.tokens;

import me.study.springsecurityjwtdemo.domain.UserRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class JwtPostProcessingToken extends UsernamePasswordAuthenticationToken {

    private JwtPostProcessingToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public JwtPostProcessingToken(String username, UserRole role) {
        // DB에 들어가는정보 아니기 때문에 실제로 1234를 비밀번호로 쳐도 인증이되거나 하는게 아님
        // 인메모리이기 떄문에 상관없을것 같은 판단
        super(username, "1234", parseAuthorities(role));
    }

    // UserRole에서 GrantedAuthority를 구현해서 쓸 수도 있음.
    private static Collection<? extends GrantedAuthority> parseAuthorities(UserRole role) {
        return Arrays.asList(role).stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    public String getUserId() {
        return (String) super.getPrincipal();
    }

    public String getPassword() {
        return (String) super.getCredentials();
    }


}
