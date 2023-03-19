package com.hu.oleg.blogproject.controller;

import com.hu.oleg.blogproject.dto.CommentDto;
import com.hu.oleg.blogproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentsController {
    private final CommentService commentService;

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<CommentDto> comment(
            @PathVariable("id") long postId,
            @RequestBody CommentDto commentDto,
            UriComponentsBuilder uriComponentsBuilder){
        var saved = commentService.createComment(postId,commentDto);
        var uri = uriComponentsBuilder
                .path("/api/v1/posts/{post_id}/{commentId}")
                .buildAndExpand(postId,saved.getId())
                .toUri();

        return  ResponseEntity.created(uri).body(saved);
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable("id") long postId){
        return ResponseEntity.ok(commentService.findCommentsByPostId(postId));
    }
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable long id){
        return ResponseEntity.ok(commentService.findCommentById(id));
    }

    @PutMapping("comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long id,@RequestBody CommentDto dto){
        return ResponseEntity.ok(commentService.updateCommentById(id,dto));
    }

    @DeleteMapping("comments/{id}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable long id){
        return ResponseEntity.ok(commentService.deleteCommentById(id));
    }
}
