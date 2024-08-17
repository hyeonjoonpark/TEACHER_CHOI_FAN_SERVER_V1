package bsm.choi.fancafe.domain.user.presentation.dto.request;

import bsm.choi.fancafe.domain.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link bsm.choi.fancafe.domain.user.User}
 */
@Builder
public record UserUpdateRequest(
        @Email String email,
        String password,
        @Size(min = 2, max = 12, message = "이름은 2자 이상 12자 이하여야 합니다")
        String name,
        String profileImageName,
        String profileImagePath,
        @Size(min = 6, max = 12, message = "닉네임은 6자 이상 12자 이하여야 합니다")
        String nickname
) implements Serializable {
    public static UserUpdateRequest of(String email, String password, String name, String profileImageName, String profileImagePath, String nickname) {
        return new UserUpdateRequest(email, password, name, profileImageName, profileImagePath, nickname);
    }

    public static UserUpdateRequest from(User user) {
        return UserUpdateRequest.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .profileImageName(user.getProfileImageName())
                .profileImagePath(user.getProfileImagePath())
                .nickname(user.getNickname())
                .build();
    }
}