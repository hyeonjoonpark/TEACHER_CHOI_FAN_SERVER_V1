package bsm.choi.fancafe.domain.user.provider;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Getter
@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 10;
    String secretKey = "dORmIToRY!sECreTkEy";

    private Key getSignInKey(String secretKey) {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String createJwt(String email) {

        return Jwts.builder()
                .signWith(getSignInKey(this.secretKey))
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(this.ACCESS_TOKEN_EXPIRE_TIME))
                .compact();
    }
}