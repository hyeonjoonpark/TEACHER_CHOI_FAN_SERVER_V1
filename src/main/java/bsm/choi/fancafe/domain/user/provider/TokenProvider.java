package bsm.choi.fancafe.domain.user.provider;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;

@Getter
@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60;
    private final String secretKey = "FAcAfeSEcrETKey";

    private Key getSignInKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String createJwt(String id) {

        return Jwts.builder()
                .signWith(getSignInKey())
                .setSubject(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(this.ACCESS_TOKEN_EXPIRE_TIME))
                .compact();
    }
}
