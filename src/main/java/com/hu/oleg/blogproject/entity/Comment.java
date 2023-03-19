package com.hu.oleg.blogproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String body;

    @ToString.Exclude //don't print the post details in toString
    @ManyToOne()//(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}

