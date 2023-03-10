package com.example.jwt_prac.repository;

import com.example.jwt_prac.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long>{
}
