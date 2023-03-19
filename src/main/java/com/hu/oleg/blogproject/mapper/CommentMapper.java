package com.hu.oleg.blogproject.mapper;

import com.hu.oleg.blogproject.dto.CommentDto;
import com.hu.oleg.blogproject.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toDto(Comment comment);
    Comment toEntity(CommentDto commentDto);
}
