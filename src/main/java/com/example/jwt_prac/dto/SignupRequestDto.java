package com.example.jwt_prac.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupRequestDto {

    @Size(min=4, max=10 ,message = "username은 4글자 이상 10글자 이하여야 합니다.")
    @Pattern(regexp = "^[a-z0-9]+$")
    private String username;

    @Valid
    @Size(min=8, max=15, message = "password는 8글자 이상 15글자 이하여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+[^A-Za-z0-9_]+$", message = "특수문자를 포함해야 합니다.")
    private String password;

    private boolean Admin = false;
    private String adminToken= "";
}
