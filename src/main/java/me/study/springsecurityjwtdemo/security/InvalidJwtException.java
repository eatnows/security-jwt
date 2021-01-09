package me.study.springsecurityjwtdemo.security;

public class InvalidJwtException extends RuntimeException{

    public InvalidJwtException(String msg) {
        super(msg);
    }
}
