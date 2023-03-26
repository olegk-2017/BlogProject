package com.hu.oleg.blogproject.service;

import com.hu.oleg.blogproject.dto.SignDto;
import com.hu.oleg.blogproject.dto.UserRegistrationResponseDto;
import com.hu.oleg.blogproject.entity.Role;
import com.hu.oleg.blogproject.error.BadRequestException;
import com.hu.oleg.blogproject.repository.RoleRepository;
import com.hu.oleg.blogproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional //stay connection to database is open
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByUsernameIgnoreCase(username)
                .orElseThrow(()->new UsernameNotFoundException(username));

        var roles = user.getRoles().stream().map(r->new SimpleGrantedAuthority(r.getName())).toList();


        return new User(user.getUsername(),user.getPassword(),roles);
    }


    public UserRegistrationResponseDto createUser(SignDto dto){

        var byEmail = userRepository.findUserByEmailIgnoreCase(dto.getEmail());
        var byUsername = userRepository.findUserByUsernameIgnoreCase(dto.getUsername());

        if (byEmail.isPresent()){
            throw new BadRequestException("email","Email already exists");
        } else if (byUsername.isPresent()) {
            throw new BadRequestException("user","user already exists");
        }

        var user = com.hu.oleg.blogproject.entity.User
                .builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Set.of((Role) roleRepository.findByNameIgnoreCase("ROLE_USER").orElseThrow().getRoles()))
                .build();

        var saved = userRepository.save(user);
        return UserRegistrationResponseDto
                .builder()
                .email(saved.getEmail())
                .username(saved.getUsername())
                .message("sign up successfully")
                .build();
    }
}
