package com.example.jwt_prac.dto;

import com.example.jwt_prac.entity.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsRequestDto {

    private String comment;
    private Posts post;

}
