package com.example.jwt_prac.repository;

import com.example.jwt_prac.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long>{

    List<Posts> findAll();

}
