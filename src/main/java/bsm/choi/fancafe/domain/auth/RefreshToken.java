package bsm.choi.fancafe.domain.auth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 2592000L) // 30일
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    @Id
    private String refreshToken;

    @TimeToLive
    private Long expiresIn;

    @Builder
    public RefreshToken(String refreshToken, Long expiresIn) {
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }
}
