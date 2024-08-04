package bsm.choi.fancafe.domain.auth.utils;

import bsm.choi.fancafe.global.redis.presentation.dao.RedisDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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
    private static final String USER_ID_KEY = "userId";

    // isExpired 메서드 구현
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

    public static UUID getUserId(String token, String secretKey) {
        // base64 인코딩된 secretKey 문자열을 디코딩합니다.
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        // 디코딩된 secretKey 바이트 배열로부터 SecretKey 인스턴스를 생성합니다.
        SecretKey originalKey = Keys.hmacShaKeyFor(decodedKey);

        // secretKey를 SecretKey 인스턴스로 사용하여 새 API를 사용합니다.
        String userId = Jwts.parserBuilder()
                .setSigningKey(originalKey)
                .build() // 파서 설정을 완료합니다.
                .parseClaimsJws(token)
                .getBody()
                .get(USER_ID_KEY, String.class);

        // userId를 UUID로 변환하여 반환합니다.
        return UUID.fromString(userId);
    }

    public static String createJwt(UUID uuid, String secretKey, Long exprTime) {
        Claims claims = Jwts.claims();
        claims.put(USER_ID_KEY, uuid.toString());

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
