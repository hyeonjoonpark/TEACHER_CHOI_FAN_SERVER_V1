package bsm.choi.fancafe.domain.auth.presentation.dto.request;

import bsm.choi.fancafe.domain.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public record SignUpRequest(
  String email,
  String password,
  String nickname
) {

    public User toEntity(PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(password);
        return User.builder()
                .email(email)
                .password(encodedPassword)
                .nickname(nickname)
                .build();
    }
}
