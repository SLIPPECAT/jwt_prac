package com.example.jwt_prac.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostsResponseDto {
    private String username;
    private String contents;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    Long id;

    public PostsResponseDto(String username, String contents, String title, LocalDateTime createdAt, LocalDateTime modifiedAt, Long id) {
        this.username = username;
        this.contents = contents;
        this.title = title;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.id = id;
    }
}
