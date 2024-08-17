package bsm.choi.fancafe.domain.auth.presentation.dto.request;

import bsm.choi.fancafe.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Builder
public record SignUpRequest(
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "유효한 Gmail 주소를 입력하세요.")
        String email,
        String password,
        @NotBlank
        @Size(min = 2, max = 12, message = "이름은 2자 이상 12자 이하여야 합니다")
        String name,
        @Size(min = 6, max = 12, message = "닉네임은 6자 이상 12자 이하여야 합니다")
        String nickname,
        MultipartFile profileImage
) implements Serializable {
    public User toEntity(PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(password);
        return User.builder()
                .email(email)
                .password(encodedPassword)
                .name(name)
                .nickname(nickname)
                .profileImageName(profileImage.getOriginalFilename())
                .profileImagePath("uploads/" + profileImage.getOriginalFilename())
                .build();
    }
}
