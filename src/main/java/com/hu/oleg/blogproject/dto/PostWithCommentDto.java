package com.hu.oleg.blogproject.dto;


import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostWithCommentDto {
    private Long id;
    private String name;
    private String description;
    private String content;
    @Builder.Default//builder will create a new hashset
    private Set<CommentDto> comments = new HashSet<>();

}

