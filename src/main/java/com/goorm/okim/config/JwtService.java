package com.goorm.okim.config;

import com.goorm.okim.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@PropertySource("classpath:application.yaml")
public class JwtService {

    @Value("${app.jwt.secret-key}")
    private String SECRET_KEY;

    private enum JwtType{
        REFRESH, ACCESS
    }


    public String generateAccessToken(User user) {
        HashMap<String, Object> hashMap = createClaims(user);
        return generateToken(hashMap, user, JwtType.ACCESS);
    }

    public String generateRefreshToken(User user) {
        HashMap<String, Object> hashMap = createClaims(user);
        return generateToken(hashMap, user, JwtType.REFRESH);
    }

    private HashMap<String, Object> createClaims(User user) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid", user.getId());
        return hashMap;
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenVaild(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpriation(token).before(new Date());
    }

    private Date extractExpriation(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extractClaims, User user, JwtType jwtType) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256);

        if (jwtType == JwtType.ACCESS) {
            jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 30 * 30));           // 하루
        }else{
            jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 30 * 30));           // 한달
        }
        return jwtBuilder.compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
