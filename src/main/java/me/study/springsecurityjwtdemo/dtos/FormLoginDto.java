package me.study.springsecurityjwtdemo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FormLoginDto {

    @JsonProperty("userId")
    private String id;

    @JsonProperty("password")
    private String password;
}
