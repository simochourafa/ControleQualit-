package com.group.azura.maraissa.controleQualite.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    public static final String SECRET = "40454667554A472D34753746B5727821B61A576E5A45068705212D6351665442";

    final AppUserDetailsService userDetailsService;

    public String extractUsername(String token) {
        log.info("Extracting username from token");
        return extractClaim(token, Claims::getSubject);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        log.info("Validating token '{}' for user: {}", token,userDetails.getUsername());
        final String username = extractUsername(token);
        return (username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(String userName) {
        log.info("Generating token for user: {}", userName);
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    public String refreshToken(String token) {
        log.info("Refreshing token");
        if (token != null && token.length() >= 7) {
            String tokenWithoutBearer = token.substring(7);
            UserDetails userDetails = userDetailsService.loadUserByUsername(extractUsername(tokenWithoutBearer));
            if (userDetails.getUsername().equals(extractUsername(tokenWithoutBearer))) {
                return generateToken(userDetails.getUsername());
            } else {
                log.error("Invalid token. Cannot refresh.");
                throw new SecurityException("Invalid token. Cannot refresh.");
            }
        } else {
            log.error("Token not existing or invalid!");
            throw new SecurityException("Token not existing or invalid!");
        }
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        log.debug("Extracting all claims from token");
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String createToken(Map<String, Object> claims, String userName) {
        log.debug("Creating token for user: {}", userName);
        return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 50))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        log.debug("Getting signing key");
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
