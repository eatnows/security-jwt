package me.study.springsecurityjwtdemo.security.providers;

import me.study.springsecurityjwtdemo.domain.Account;
import me.study.springsecurityjwtdemo.domain.AccountRepository;
import me.study.springsecurityjwtdemo.security.AccountContext;
import me.study.springsecurityjwtdemo.security.AccountContextService;
import me.study.springsecurityjwtdemo.security.tokens.PostAuthorizationToken;
import me.study.springsecurityjwtdemo.security.tokens.PreAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;

public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountContextService accountContextService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        PreAuthorizationToken token = (PreAuthorizationToken) authentication;

        String username = token.getUsername();
        String password = token.getUserPassword();

        Account account = accountRepository.findByUserId(username)
                .orElseThrow(() -> new NoSuchElementException("정보에 맞는 계정이 없습니다."));

        if (isCorrectPassword(password, account)) {
            return PostAuthorizationToken.getTokenFromAccountContext(AccountContext.fromAccountModel(account));
        }

        throw new NoSuchElementException("인증 정보가 정확하지 않습니다.");
    }

    // PreAuthorizationToken로 들어오는 요청은 이제 이 필터에 걸리게 된다.
    @Override
    public boolean supports(Class<?> aClass) {
        return PreAuthorizationToken.class.isAssignableFrom(aClass);
    }

    private boolean isCorrectPassword(String password, Account account) {
        // 원본(비교 대상이 될 패스워드)가 매개변수 앞에 와야함
        // 순서가 바뀌면 에러가 난다.
        return passwordEncoder.matches(account.getPassword(), password);
    }
}
