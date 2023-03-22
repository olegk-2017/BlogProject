package com.hu.oleg.blogproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 2)
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 5)
    private String password;//encrypted

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns =
                    @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns =
                    @JoinColumn(name = "role_id",
                    referencedColumnName = "id")
    )
    private Set<Role> roles;
}
