package me.study.springsecurityjwtdemo.security;


import me.study.springsecurityjwtdemo.domain.Account;
import me.study.springsecurityjwtdemo.domain.UserRole;
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


    public AccountContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static AccountContext fromAccountModel(Account account) {
        return new AccountContext(account.getUserId(), account.getPassword(), parseAuthorities(account.getUserRole()));
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(UserRole role) {
        return Arrays.asList(role).stream().map(r -> new SimpleGrantedAuthority(r.name())).collect(Collectors.toList());
    }
}
