package com.nando.lms.util;

import com.nando.lms.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Data
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExp;
    private Set<String> tokenStorage = new HashSet<>();
    private Set<String> revokedToken = new HashSet<>();

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .setExpiration(new Date(System.currentTimeMillis() + jwtExp))
                .compact();
    }

    public boolean validateToken(String authorizationHeader) {
        if (authorizationHeader == null) {
            throw new UnauthorizedException();
        }

        String token = authorizationHeader.replace("Bearer ", "");

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("Token expired");
        } catch (UnsupportedJwtException e) {
            throw new UnauthorizedException("Token unsupported");
        } catch (MalformedJwtException e) {
            throw new UnauthorizedException("Token malformed");
        } catch (SignatureException e) {
            throw new UnauthorizedException("Signature unknown");
        }
    }

    public boolean isTokenRevoked(String token) {
        return revokedToken.contains(token);
    }

    public void addTokenToStorage(String token) {
        this.tokenStorage.add(token);
    }

    public boolean removeTokenFromStorage(String token) {
        return this.tokenStorage.remove(token);
    }

    public void addRevokedToken(String token) {
        this.revokedToken.add(token);
    }
}
