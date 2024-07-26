package bsm.choi.fancafe.domain.user.presentation.dto.response;

import bsm.choi.fancafe.domain.user.types.GradeType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link bsm.choi.fancafe.domain.user.User}
 */
@Builder
public record UserDetailResponse(
        @NotNull @Email String email,
        @NotNull @Size(min = 2, max = 12) String name,
        @NotNull String profileImage,
        @NotNull GradeType gradeType
) implements Serializable {

}