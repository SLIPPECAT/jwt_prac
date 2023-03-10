package com.example.jwt_prac.contoller;


import com.example.jwt_prac.dto.PostsRequestDto;
import com.example.jwt_prac.dto.PostsResponseDto;
import com.example.jwt_prac.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/posts")
    public ResponseEntity createPosts(@RequestBody PostsRequestDto requestDto, HttpServletRequest servletRequestDto){
        postsService.createPosts(requestDto, servletRequestDto);
        String message = "게시글이 등록되었습니다.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public List<PostsResponseDto> getPosts(){
        return postsService.getPosts();
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity updatePosts(@PathVariable Long id, @RequestBody PostsRequestDto requestDto, HttpServletRequest servletRequestDto){
        postsService.updatePosts(id, requestDto, servletRequestDto);
        String message = "게시글이 수정되었습니다.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity deletePosts(@PathVariable Long id, HttpServletRequest servletRequestDto){
        postsService.deletePosts(id, servletRequestDto);
        String message = "게시글이 삭제되었습니다.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
