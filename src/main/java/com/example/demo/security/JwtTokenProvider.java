package com.example.demo.security;

import com.example.demo.model.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String JWT_SECRET = "secretkey123";
    private final long JWT_EXPIRATION = 86400000; // 1 day

    // REQUIRED BY TESTS
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // REQUIRED BY TESTS
    public boolean validateToken(String token, User user) {
        try {
            String email = extractUsername(token);
            return email.equals(user.getEmail()) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    // REQUIRED BY TESTS
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // REQUIRED BY TESTS
    public Long getUserIdFromToken(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    // REQUIRED BY TESTS
    public String getRoleFromToken(String token) {
        return getClaims(token).get("role", String.class);
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
