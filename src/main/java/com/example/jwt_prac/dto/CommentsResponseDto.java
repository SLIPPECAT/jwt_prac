package com.example.jwt_prac.dto;

import com.example.jwt_prac.entity.Comments;
import com.example.jwt_prac.entity.Posts;
import com.example.jwt_prac.entity.Timestamped;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentsResponseDto extends Timestamped {

    Long id;
    String comment;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    public CommentsResponseDto(Comments comments){
        this.comment = comments.getComment();
        this.createdAt = comments.getCreatedAt();
        this.modifiedAt = comments.getModifiedAt();
        this.id = comments.getId();
    }
}
