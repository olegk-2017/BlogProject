package com.hu.oleg.blogproject.mapper;

import com.hu.oleg.blogproject.dto.PostDto;
import com.hu.oleg.blogproject.dto.PostWithCommentDto;
import com.hu.oleg.blogproject.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDto toDto(Post post);
    Post toEntity(PostDto postDto);
    PostWithCommentDto toDtoWithComments(Post post);
}
