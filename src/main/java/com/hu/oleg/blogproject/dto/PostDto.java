package com.hu.oleg.blogproject.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2, message = "Title must contain at least 2 char")
    private String title;
    @NotNull
    @Size(min = 3, message = "Title must contain at least 3 char")
    private String Description;
    @NotNull
    @NotEmpty
    private String content;

//    private Set<CommentDto> comments = new HashSet<>();
}