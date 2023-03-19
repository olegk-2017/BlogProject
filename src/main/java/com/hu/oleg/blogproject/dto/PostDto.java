package com.hu.oleg.blogproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.hu.oleg.blogproject.entity.Post} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PostDto implements Serializable {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String Description;
    @NotNull
    private String content;

//    private Set<CommentDto> comments = new HashSet<>();
}