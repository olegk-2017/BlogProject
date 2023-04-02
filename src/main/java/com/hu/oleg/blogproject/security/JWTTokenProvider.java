package com.hu.oleg.blogproject.security;

import com.hu.oleg.blogproject.error.BadRequestException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@AllArgsConstructor
@RequiredArgsConstructor
public class JWTTokenProvider {

    @Value("${blog.expire}")
    private String expires;
    @Value("${blog.secret}")
    private String secret;

    private Key mSecret = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    private Key oSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

    public String generateTokenForUserName(String username) {
        var curentDate = new Date();
        var expiresDate = new Date(curentDate.getTime() + Long.parseLong(expires));
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiresDate)
                .setIssuedAt(curentDate)
                .signWith(mSecret)
                .compact();
    }
    public String getUserNameFromToken(String jwt){
        try {

            return Jwts.parserBuilder()
                    .setSigningKey(mSecret)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();
        }catch (ExpiredJwtException e){
            throw new BadRequestException("Token","Exprired");
        }catch (MalformedJwtException e){
            throw new BadRequestException("Token","Invalid");
        }catch (JwtException e){
            throw new BadRequestException("Token","Exeption" + e.getMessage());
        }
    }
    public boolean validateToken(String jwt){
        try {

             Jwts.parserBuilder()
                    .setSigningKey(mSecret)
                    .build()
                    .parseClaimsJws(jwt);
        }catch (ExpiredJwtException e){
            throw new BadRequestException("Token","Exprired");
        }catch (MalformedJwtException e){
            throw new BadRequestException("Token","Invalid");
        }catch (JwtException e){
            throw new BadRequestException("Token","Exeption" + e.getMessage());
        }
        return true;
    }
}
