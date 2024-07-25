package bsm.choi.fancafe.domain.auth;

import bsm.choi.fancafe.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Entity
@Getter
@RedisHash("refreshToken")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    @Id
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String token;
    @TimeToLive
    private Long expiresIn;

    @Builder
    public RefreshToken(String id, User user, String token, Long expiresIn) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public static RefreshToken from(String id, User user, String token, Long expiresIn) {
        return RefreshToken.builder()
                .id(id)
                .user(user)
                .token(token)
                .expiresIn(expiresIn / 1000L)
                .build();
    }
}
