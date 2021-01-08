package me.study.springsecurityjwtdemo.domain;

public enum UserRole {

    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }
}
