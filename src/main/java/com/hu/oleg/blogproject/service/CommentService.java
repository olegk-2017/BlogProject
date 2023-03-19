package com.hu.oleg.blogproject.service;

import com.hu.oleg.blogproject.dto.CommentDto;

import java.util.List;

public interface CommentService {  // interface as a contract : what we want to do.
    CommentDto createComment(long postId, CommentDto dto);

    List<CommentDto> findCommentsByPostId(long postId);

    CommentDto findCommentById(long commentId);

    CommentDto updateCommentById(long id, CommentDto commentDto);

    CommentDto deleteCommentById(long id);

}
