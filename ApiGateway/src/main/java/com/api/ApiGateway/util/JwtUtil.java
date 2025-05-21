package com.api.ApiGateway.util;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static final String SECRET = "XIART3YMFREGoQwt9RbwxdcmJLGm9AhY4Ooc8UaMQkQ="; // Same as UserService
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ✅ Validate token
    public boolean validateToken(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Extract claims
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ Extract username (email)
    public String getUsername(Claims claims) {
        return claims.getSubject(); // email was set as subject
    }

    // ✅ Extract roles
    public List<String> getRoles(Claims claims) {
        return claims.get("roles", List.class);
    }

    // ✅ Convert roles to Spring Authorities
    public List<SimpleGrantedAuthority> getAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}