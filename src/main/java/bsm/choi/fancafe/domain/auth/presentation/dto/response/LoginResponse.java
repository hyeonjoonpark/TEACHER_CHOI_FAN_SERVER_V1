package bsm.choi.fancafe.domain.auth.presentation.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record LoginResponse(
        String message,
        HttpStatus status,
        String email,
        String accessToken,
        String refreshToken

) {
    public static LoginResponse of(String message, HttpStatus status, String email, String accessToken, String refreshToken) {
        return LoginResponse.builder()
                .message(message)
                .status(status)
                .email(email)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
