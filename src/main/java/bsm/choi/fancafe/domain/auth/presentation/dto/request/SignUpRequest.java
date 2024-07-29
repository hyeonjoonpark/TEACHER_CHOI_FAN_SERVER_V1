package bsm.choi.fancafe.domain.auth.presentation.dto.request;

import bsm.choi.fancafe.domain.user.User;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.crypto.password.PasswordEncoder;

public record SignUpRequest(
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "유효한 Gmail 주소를 입력하세요.")
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
