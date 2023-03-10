package com.example.jwt_prac.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    private String username;
    private String password;
    private boolean Admin = false;
    private String adminToken= "";
}
