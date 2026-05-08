package com.resumeanalyzer.service;

import java.security.Key;
import java.util.Date;
import java.util.jar.JarException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private long jwtExpirationMs;


    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());        
    }

    public String generateToken(String email){
        return Jwts.builder().setSubject(email).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+jwtExpirationMs)).signWith(getSigningKey()).compact();
    }

    public String extractEmail(String token){
        try{
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        }catch(Exception e){
            return null;
        }
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }
}
