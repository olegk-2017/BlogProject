package com.hu.oleg.blogproject.repository;


import com.hu.oleg.blogproject.entity.Role;
import com.hu.oleg.blogproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<User> findByNameIgnoreCase(String name);
}
