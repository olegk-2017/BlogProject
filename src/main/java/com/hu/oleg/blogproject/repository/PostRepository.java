package com.hu.oleg.blogproject.repository;


import com.hu.oleg.blogproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
