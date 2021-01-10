package me.study.springsecurityjwtdemo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
@Data
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ACCOUNT_USERNAME")
    private String username;

    @Column(name = "ACCOUNT_LOGINID")
    private String userId = "emalnyun@naver.com";

    @Column(name = "ACCOUNT_PASSWORD")
    private String password = "1234";

    @Column(name = "ACCOUNT_ROLE")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    @Column(name = "ACCOUNT_SOCIAL_ID")
    private Long socialId;

    @Column(name = "ACCOUNT_SOCIAL_PROVIDER")
    @Enumerated(value = EnumType.STRING)
    private SocialProviders socialProvider;

    @Column(name = "ACCOUNT_SOCIAL_PROFILEPIC")
    private String profileHref;

    public Account() {
    }

    public Account(Long id, String username, String userId, String password, UserRole userRole, Long socialId, SocialProviders socialProvider, String profileHref) {
        this.id = id;
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.userRole = userRole;
        this.socialId = socialId;
        this.socialProvider = socialProvider;
        this.profileHref = profileHref;
    }
}
