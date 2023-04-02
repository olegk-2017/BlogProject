package com.hu.oleg.blogproject.controller;

import com.hu.oleg.blogproject.dto.SignInDto;
import com.hu.oleg.blogproject.dto.SignUpDto;
import com.hu.oleg.blogproject.security.JWTTokenProvider;
import com.hu.oleg.blogproject.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {
    private final CustomUserDetailsService customUserDetailsService;

    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<Object> sigIn(@RequestBody SignInDto dto){
        var athToken = new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword());

        //Spring security authentication checks the validity
        var authentication = authenticationManager.authenticate(athToken);

        //issue JWT
        var jwt = tokenProvider.generateTokenForUserName(dto.getUsername());


        return ResponseEntity.ok(Map.of("message","Sign is successfully","token",jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody SignUpDto dto){

        return new ResponseEntity<>(customUserDetailsService.createUser(dto), HttpStatus.CREATED);
    }
}
