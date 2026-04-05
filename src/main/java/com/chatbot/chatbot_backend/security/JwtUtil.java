package com.chatbot.chatbot_backend.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // Must be at least 32 characters for HS256
    private static final String SECRET = "chatbotsaas_super_secret_key_2024_must_be_32_chars_long_here";
    private static final long EXPIRY = 1000 * 60 * 60 * 24; // 24 hours

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String email, String clientId) {
        return Jwts.builder()
                .subject(email)
                .claim("clientId", clientId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRY))
                .signWith(getKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public String extractClientId(String token) {
        return getClaims(token).get("clientId", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}