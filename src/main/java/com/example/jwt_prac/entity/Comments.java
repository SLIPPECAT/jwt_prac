package com.example.jwt_prac.entity;

import com.example.jwt_prac.dto.CommentsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comments extends Timestamped{

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    public Comments(CommentsRequestDto requestDto, Posts posts){
        this.comment = requestDto.getComment();
        this.posts = posts;
    }
}