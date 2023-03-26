package com.hu.oleg.blogproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)//print filters to the log
@EnableMethodSecurity
public class DefaultSecurityConfig {

    @Bean
    AuthenticationManager authorizationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/api/v1/auth/**").permitAll();
                    auth.requestMatchers("/api/v1/**").authenticated();
                    auth.anyRequest().permitAll();
                })
                .httpBasic(basic->
                    basic.authenticationEntryPoint(new BlocAuthenticationEntryPoint()))
                .build();

    }
//    @Bean
//    UserDetailsService userDetailsService(){
//        UserDetails user1 = User.builder().username("user").password(passwordEncoder().encode("user")).roles("USER").build();
//        UserDetails user2 = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(
//                user1,user2
//        );
//        //admin:
//        //{bcrypt}$2a$10$Ua.woV66urtrsBPn81h72OV5VIGKEb4b.oi.VdSp4bSsQhCFtc08m
//        //user:
//        //{bcrypt}$2a$10$qdcDz2RTU2L18jI/R7ITRewZvX0cvK0UFetBkW90P9xHyTwK.ai36
//
//    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
