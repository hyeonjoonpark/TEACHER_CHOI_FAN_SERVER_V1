package bsm.choi.fancafe.domain.user.presentation.dto.response;

import bsm.choi.fancafe.domain.user.User;
import bsm.choi.fancafe.domain.user.types.GradeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link bsm.choi.fancafe.domain.user.User}
 */
@Builder
public record UserDetailResponse(
        @NotBlank @Size(min = 2, max = 12) String name,
        @NotBlank String profileImage,
        @NotBlank GradeType gradeType
) implements Serializable {
    public static UserDetailResponse of(String name, String profileImage, GradeType gradeType) {
        return new UserDetailResponse(name, profileImage, gradeType);
    }

    public static UserDetailResponse from(User user) {
        return UserDetailResponse.builder()
                .name(user.getName())
                .profileImage(user.getProfileImage())
                .gradeType(user.getGradeType())
                .build();
    }
}