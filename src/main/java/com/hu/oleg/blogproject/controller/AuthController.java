package com.hu.oleg.blogproject.controller;

import com.hu.oleg.blogproject.dto.SignDto;
import com.hu.oleg.blogproject.entity.Role;
import com.hu.oleg.blogproject.entity.User;
import com.hu.oleg.blogproject.repository.RoleRepository;
import com.hu.oleg.blogproject.repository.UserRepository;
import com.hu.oleg.blogproject.service.CustomUserDetailsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {
    private final CustomUserDetailsService customUserDetailsService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<Object> sigIn(SignDto dto){
        var athToken = new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword());

        //Spring security authentication checks the validity
        var authentication = authenticationManager.authenticate(athToken);

        return ResponseEntity.ok(Map.of("message","Sign is successfully"));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody SignDto dto){

        return new ResponseEntity<>(customUserDetailsService.createUser(dto), HttpStatus.CREATED);
    }
}
