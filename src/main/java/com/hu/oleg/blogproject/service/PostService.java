package com.hu.oleg.blogproject.service;

import com.hu.oleg.blogproject.dto.PostDto;
import com.hu.oleg.blogproject.dto.PostPageDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto dto);

    List<PostDto> getAllPost();

    PostDto getPostById(Long id);

    PostDto updatePostById(Long id, PostDto dto);

    void deletePostById(Long id);

    PostPageDto getallPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
