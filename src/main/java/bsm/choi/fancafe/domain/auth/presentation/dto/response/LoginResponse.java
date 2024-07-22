package bsm.choi.fancafe.domain.auth.presentation.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
  String token,
  String email
) {
    public static LoginResponse of(String token, String email) {
        return LoginResponse.builder()
                .token(token)
                .email(email)
                .build();
    }
}
