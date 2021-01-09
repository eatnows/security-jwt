package me.study.springsecurityjwtdemo.security;


import me.study.springsecurityjwtdemo.domain.Account;
import me.study.springsecurityjwtdemo.domain.UserRole;
import me.study.springsecurityjwtdemo.security.tokens.JwtPostProcessingToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/*
스프링에서 제공해주는 UserDetails를 구현한 User 클래스
 */
public class AccountContext extends User {

    private Account account;

    public AccountContext(Account account, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.account = account;
    }

    public AccountContext(String username, String password, String role) {
        super(username, password, parseAuthorities(role));
    }

    public static AccountContext fromAccountModel(Account account) {
        return new AccountContext(account, account.getUserId(), account.getPassword(), parseAuthorities(account.getUserRole()));
    }

    public static AccountContext fromJwtPostToken(JwtPostProcessingToken token) {
        return new AccountContext(null, token.getUserId(), token.getPassword(), token.getAuthorities());
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(UserRole role) {
        return Arrays.asList(role).stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    public static List<SimpleGrantedAuthority> parseAuthorities(String role) {
        return parseAuthorities(UserRole.getRoleByName(role));
    }

    public Account getAccount() {
        return account;
    }
}
