package com.bfu.courseProject.services.implementations;

import com.bfu.courseProject.entities.User;
import com.bfu.courseProject.security.CustomUserPrincipal;
import com.bfu.courseProject.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public String generateToken(User user) {
        Instant expirationDate = Instant.now().plus(1, ChronoUnit.HOURS);
        Date date = Date.from(expirationDate);

        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        String token = Jwts.builder()
                .claim("id", user.getId())
                .claim("sub", user.getEmail())
                .claim("admin", user.getAdmin())
                .setExpiration(date)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return "Bearer " + token;
    }

    @Override
    public CustomUserPrincipal parseToken(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build()
                .parseClaimsJws(token);

        Claims body = claimsJws.getBody();

        String email = body
                .getSubject();

        Long id = body
                .get("id", Long.class);

        boolean isAdmin = body
                .get("admin", Boolean.class);

        return new CustomUserPrincipal(id, email, isAdmin);
    }
}
