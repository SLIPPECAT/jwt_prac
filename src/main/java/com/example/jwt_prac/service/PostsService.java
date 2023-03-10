package com.example.jwt_prac.service;


import com.example.jwt_prac.dto.PostsRequestDto;
import com.example.jwt_prac.dto.PostsResponseDto;
import com.example.jwt_prac.entity.Posts;
import com.example.jwt_prac.jwt.JwtUtil;
import com.example.jwt_prac.repository.PostsRepository;
import com.example.jwt_prac.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public List<PostsResponseDto> getPosts() {
        List<Posts> posts = postsRepository.findAll();
        List<PostsResponseDto> postsResponseDtos = new ArrayList<>();
        for (Posts post : posts) {
            PostsResponseDto postResponseDto = new PostsResponseDto(post.getUsername(), post.getContent(), post.getTitle(), post.getCreatedAt(), post.getModifiedAt(), post.getId());
            postsResponseDtos.add(postResponseDto);
        }
        return postsResponseDtos;
    }

    @Transactional
    public void createPosts(PostsRequestDto requestDto, HttpServletRequest servletRequestDto) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(servletRequestDto);
        Claims claims;

        // 토큰이 있는 경우에만 게시글 등록
        if (token != null ) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰 오류가 발생했습니다.");
            }
            Posts post = new Posts(requestDto);
            postsRepository.save(post);
        }
    }

    @Transactional
    public void updatePosts(Long id, PostsRequestDto requestDto, HttpServletRequest servletRequestDto) {
        String token = jwtUtil.resolveToken(servletRequestDto);
        Claims claims;
        // 토큰이 있는 경우에만 게시글 수정
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰 오류가 발생했습니다.");
            }

            System.out.println(requestDto.getUsername());
            System.out.println(requestDto.getTitle());
            // 게시글 아이디로 찾아오기
            Posts post = postsRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );
            post.update(requestDto);
            postsRepository.save(post);
        }
    }

    @Transactional
    public void deletePosts(Long id, HttpServletRequest servletRequestDto) {
        String token = jwtUtil.resolveToken(servletRequestDto);
        Claims claims;

        // 토큰이 있는 경우에만 게시글 삭제
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰 오류가 발생했습니다.");
            }

            Posts posts = postsRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );
            postsRepository.delete(posts);
        }
    }
}
