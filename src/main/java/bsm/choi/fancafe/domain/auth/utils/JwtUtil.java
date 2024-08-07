package bsm.choi.fancafe.domain.auth.utils;

import bsm.choi.fancafe.global.redis.presentation.dao.RedisDao;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final RedisDao redisDao;
    private static final String USER_ID_KEY = "USERID";

    public static boolean isExpired(String token, String secretKey) {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        SecretKey originalKey = Keys.hmacShaKeyFor(decodedKey);
        return Jwts.parserBuilder()
                .setSigningKey(originalKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public static Boolean validateToken(String token, String secretKey) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(secretKey);
            SecretKey originalKey = Keys.hmacShaKeyFor(decodedKey);
            Jwts.parserBuilder().setSigningKey(originalKey).build().parseClaimsJws(token);
            return true;
        } catch (
                SignatureException |
                MalformedJwtException |
                ExpiredJwtException |
                UnsupportedJwtException |
                IllegalArgumentException e
        ) {
            return false;
        }

    }

    public static UUID getUserId(String token, String secretKey) {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        SecretKey originalKey = Keys.hmacShaKeyFor(decodedKey);

        String userId = Jwts.parserBuilder()
                .setSigningKey(originalKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(USER_ID_KEY, String.class);

        return UUID.fromString(userId);
    }

    public static String createJwt(UUID uuid, String secretKey, Long exprTime) {
        Claims claims = Jwts.claims();
        claims.put(USER_ID_KEY, uuid);

        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        SecretKey key = Keys.hmacShaKeyFor(decodedKey);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + exprTime))
                .compact();
    }
}
