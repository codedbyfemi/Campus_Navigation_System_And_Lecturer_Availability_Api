package com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.util;

import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.dto.JwtTokenData;
import com.example.campus_navigation_system_and_lecturer_availability_api.modules.auth.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {


    private final String jwtSecret = "5a8d6f89b9e249e3b8a1f78e7e9db53e3faba23b529dbdf6c9fead05f4b92d1a68a91e1472b19118a6795487428f04d4=";

    // convert your secret string to a secure key object
    Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    public JwtTokenData generateToken(User user) {
        Date now = new Date();
        long jwtExpiration = 86400000;
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        String token = Jwts.builder()
                .setSubject(user.getEmail()) // sets the subject of the token
                .claim("role", user.getRole().name()) // custom claim
                .setIssuedAt(now) // time the token was issued
                .setExpiration(expiryDate) // expiration date
                .signWith(key, SignatureAlgorithm.HS512) // signing method and secret
                .compact(); // build the token
        return new JwtTokenData(
                token,
                expiryDate
        );
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // ← returns the email you set as subject
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // get email from token
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration(); // ← pull expiry date from token
        return expiration.before(new Date()); // ← true if now is after expiry
    }

}