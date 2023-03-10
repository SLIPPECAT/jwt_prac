package com.example.jwt_prac.contoller;

import com.example.jwt_prac.dto.CommentsRequestDto;
import com.example.jwt_prac.dto.CommentsResponseDto;
import com.example.jwt_prac.dto.PostsResponseDto;
import com.example.jwt_prac.entity.Posts;
import com.example.jwt_prac.repository.PostsRepository;
import com.example.jwt_prac.service.CommentsService;
import com.example.jwt_prac.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Controller
public class CommentsController {

    private final CommentsService commentsService;
    private final PostsRepository postsRepository;

    @PostMapping("/{postid}/comments")
    public ResponseEntity<?> addComment(@PathVariable Long postid, @RequestBody CommentsRequestDto commentRequestDto, HttpServletRequest servletRequest){
        Posts posts = postsRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        commentsService.addComments(posts, commentRequestDto, servletRequest);
        String message = "댓글 등록이 완료됐습니다.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // @ResponseBody 위치 기억해냈다구!
    @GetMapping("/{postsid}")
    public @ResponseBody List<CommentsResponseDto> getComments(@PathVariable Long postsid){
        return commentsService.getComments(postsid);
    }

//    @PutMapping("/comments/{id}")
//    public ResponseEntity
}
