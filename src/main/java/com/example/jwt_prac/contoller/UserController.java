package com.example.jwt_prac.contoller;


import com.example.jwt_prac.dto.LoginRequestDto;
import com.example.jwt_prac.dto.SignupRequestDto;
import com.example.jwt_prac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignupRequestDto requestDto){
        userService.signUp(requestDto);
        String message = "회원가입이 완료되었습니다.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto requestDto, HttpServletResponse responseDto){
        userService.login(requestDto, responseDto);
        String message = "로그인이 완료되었습니다.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
