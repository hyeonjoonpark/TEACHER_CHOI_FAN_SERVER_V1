package bsm.choi.fancafe.domain.user.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    public static String createJwt(String id, String email, String secretKey, Long exprTime) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        claims.put("email", email);

        System.out.println("a");

        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + exprTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }
}