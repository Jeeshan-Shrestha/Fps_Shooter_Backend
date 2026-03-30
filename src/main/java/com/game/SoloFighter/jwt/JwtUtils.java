package com.game.SoloFighter.jwt;

import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.game.SoloFighter.model.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    final private String secretKey = "change-this-shit-later-dumass-sdkfjhskdfjhalksdjfhlakjsdfhlkjashfdjhlk";
    final private SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

    public String generateToken(Users userDetails){

        HashMap<String,Object> claims = new HashMap<>();
        claims.put("username",userDetails.getUsername());
        claims.put("gmail",userDetails.getGmail());
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 24 * 365))
                .claims(claims)
                .signWith(key)
                .compact();
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public String extractUsername(String token) {
        return (String)extractAllClaims(token).get("username");
    }

    public String extractEmail(String token){
        return (String)extractAllClaims(token).get("gmail");
    }

    public boolean validateToken(String username, UserDetails userDetails, String token){
        return username.equals(userDetails.getUsername()) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}