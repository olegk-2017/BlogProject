package com.hu.oleg.blogproject.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;

}

