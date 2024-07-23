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
@RedisHash("logoutAccessToken")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogoutAccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String token;

    @TimeToLive
    private Long expiresIn;

    @Builder
    public LogoutAccessToken(User user, String token, Long expiresIn) {
        this.user = user;
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public static LogoutAccessToken from(User user, String token, Long expiresIn) {
        return LogoutAccessToken.builder()
                .user(user)
                .token(token)
                .expiresIn(expiresIn)
                .build();
    }
}
