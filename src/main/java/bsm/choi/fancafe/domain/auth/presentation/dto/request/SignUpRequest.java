package bsm.choi.fancafe.domain.auth.presentation.dto.request;

import bsm.choi.fancafe.domain.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public record SignUpRequest(
  String email,
  String password,
  String name
) {

    public User toEntity(PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(password);
        return User.builder()
                .email(email)
                .password(encodedPassword)
                .name(name)
                .build();
    }
}
