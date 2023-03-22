package com.hu.oleg.blogproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)//print filters to the log
public class DefaultSecurityConfig {
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        return http
//                .csrf()
//                .and()
//                .httpBasic()
//                .and()
//                .formLogin()
//                .and()
//                .build();
        return http
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/api/v1/auth/**").permitAll();
                    auth.requestMatchers("/api/v1/**").authenticated();
                    auth.anyRequest().permitAll();
                })
                .httpBasic((a)->{})
                .build();

    }
}
