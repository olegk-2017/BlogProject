package com.hu.oleg.blogproject.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class PostPageDto {
    private List<PostWithCommentDto> results;
    private int totalPages;
    private long totalElements;
    private boolean isLast;
    private int pageNo;
    private int pageSize;
}
