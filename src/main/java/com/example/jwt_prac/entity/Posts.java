package com.example.jwt_prac.entity;


import com.example.jwt_prac.dto.PostsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Posts extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToMany(mappedBy = "posts")
    private List<Comments> comments = new ArrayList<>();

    public Posts(PostsRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.content = requestDto.getContents();
    }

    public void update(PostsRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.content = requestDto.getContents();
    }
}
