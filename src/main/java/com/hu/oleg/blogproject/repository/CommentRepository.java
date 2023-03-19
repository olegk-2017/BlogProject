package com.hu.oleg.blogproject.repository;

import com.hu.oleg.blogproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByPostId(long postId);
    List<Comment> findCommentsByEmailAndName(String email, String name);

}
