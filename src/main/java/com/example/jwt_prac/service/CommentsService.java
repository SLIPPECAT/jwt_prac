package com.example.jwt_prac.service;

import com.example.jwt_prac.dto.CommentsRequestDto;
import com.example.jwt_prac.dto.CommentsResponseDto;
import com.example.jwt_prac.entity.Comments;
import com.example.jwt_prac.entity.Posts;
import com.example.jwt_prac.jwt.JwtUtil;
import com.example.jwt_prac.repository.CommentsRepository;
import com.example.jwt_prac.repository.PostsRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final PostsRepository postsRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public List<CommentsResponseDto> getComments(Long postid) {
        Posts posts = postsRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        List<Comments> comments = posts.getComments();
        List<CommentsResponseDto> commentsResponseDtos = new ArrayList<>();
        for(Comments comment : comments){
            CommentsResponseDto commentsResponseDto = new CommentsResponseDto(comment);
            commentsResponseDtos.add(commentsResponseDto);
        }
        // 추천방식: for 문 안돌리고 해보고 결과값 살펴보기, 오 일단해봐
        return commentsResponseDtos;
    }
    // 게시글마다 식별자가 생김
    // 게시글 조회하는 API가 있음.
    // 게시글 건건마다 아이디가 있음.
    // 게시글 아이디는 웹쪽에서 보내줄 것
    // 응답을 id로

    @Transactional
    public void addComments(Posts posts, CommentsRequestDto commentRequestDto, HttpServletRequest servletRequest) {
        // 토큰 분해
        String token = jwtUtil.resolveToken(servletRequest);
        Claims claims;  // 정보 가져올 거

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효한 토큰이 아닙니다.");
            }
        }

        Comments comments = new Comments(commentRequestDto, posts);
        commentsRepository.save(comments);
    }
}
