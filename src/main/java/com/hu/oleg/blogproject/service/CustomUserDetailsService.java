package com.hu.oleg.blogproject.service;

import com.hu.oleg.blogproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByUsernameIgnoreCase(username)
                .orElseThrow(()->new UsernameNotFoundException(username));

        var roles = user.getRoles().stream().map(r->new SimpleGrantedAuthority(r.getName())).toList();


        return new User(user.getUsername(),user.getPassword(),roles);
    }
}
