package bsm.choi.fancafe.domain.user.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link bsm.choi.fancafe.domain.user.User}
 */
@Builder
public record UserUpdateRequest(
        @NotBlank String token,
        @Email String email,
        String password,
        @Size(min = 3, max = 12) String name,
        String profileImage
) implements Serializable {

}