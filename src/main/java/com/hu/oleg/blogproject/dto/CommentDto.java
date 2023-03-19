package com.hu.oleg.blogproject.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentDto {
    private Long id;
    @NotNull
    @NotEmpty(message = "must not be empty")
    private String name;
    @NotNull
    @NotEmpty(message = "must not be empty")
    private String email;
    @NotNull
    @Size(min = 2,message = "body must contain at lest 2 character")
    private String body;

}

