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

    @Column(name = "ACCOUNT_SOCIAL_PROFILEPIC")
    private String profileHref;
}
