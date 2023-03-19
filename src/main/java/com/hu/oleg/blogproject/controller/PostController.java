package com.hu.oleg.blogproject.controller;


import com.hu.oleg.blogproject.dto.PostDto;
import com.hu.oleg.blogproject.dto.PostPageDto;
import com.hu.oleg.blogproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto, UriComponentsBuilder uriBuilder){
        var saved = postService.createPost(postDto);
        var uri = uriBuilder.path("/api/v1/posts/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @GetMapping
    public ResponseEntity<Collection<PostDto>> getAllPost(){
        return ResponseEntity.ok(postService.getAllPost());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deletePostById(@PathVariable long id){
        postService.deletePostById(id);
        return ResponseEntity.ok(Map.of("message","Delited successfuly"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        return ResponseEntity.ok(
                postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable long id, @RequestBody PostDto dto){
        return ResponseEntity.ok(
                postService.updatePostById(id,dto));
    }
    @GetMapping("/page")
    public ResponseEntity<PostPageDto> pageAllPost(
                                                     @RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo,
                                                     @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
                                                     @RequestParam(value = "sortBy", defaultValue = "id",required = false) String sortBy,
                                                     @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir){
        return ResponseEntity.ok(postService.getallPosts(pageNo,pageSize,sortBy,sortDir));
    }

}
