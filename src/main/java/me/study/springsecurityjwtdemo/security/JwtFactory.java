package me.study.springsecurityjwtdemo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import me.study.springsecurityjwtdemo.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Component
public class JwtFactory {

    private static final Logger log = LoggerFactory.getLogger(JwtFactory.class);
    private static String signingKey = "jwttest";

    public String generateToken(AccountContext context) {

        String token = null;

        try {
            // 실제로는 claim에 더 많은 정보가 들어가야 하지만
            // 현재는 간단하게 유저의 권한만으로 만듦
            // 이걸 이제 SuccessHandler에서 인젝션 받아서 사용하면된다.
            token = JWT.create()
                    .withIssuer("eatnows")
                    .withClaim("USER_ROLE", context.getAccount().getUserRole().name())
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return token;
    }

    private Algorithm generateAlgorithm() throws UnsupportedEncodingException {
        return Algorithm.HMAC256(signingKey);
    }

}
