package com.example.jwt_prac.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsRequestDto {
    private String username;
    private String contents;
    private String title;
}
