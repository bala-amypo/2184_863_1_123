package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtTokenProvider {

    private static final String SECRET =
            "ThisIsASecretKeyForJwtGenerationThisIsASecretKeyForJwtGeneration";
    private static final long EXPIRATION = 24 * 60 * 60 * 1000; // 24 hours

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ✅ Generate token
    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Get claims
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ Extract email
    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ Extract userId
    public Long getUserIdFromToken(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    // ✅ Extract role
    public String getRoleFromToken(String token) {
        return getClaims(token).get("role", String.class);
    }
}
