package bsm.choi.fancafe.domain.user.presentation.dto.request;

import bsm.choi.fancafe.domain.user.types.RoleType;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link bsm.choi.fancafe.domain.user.User}
 */
@Builder
public record UserRoleUpdateRequest(
        @Size(message = "닉네임은 6자 이상 12자 이하여야 합니다", min = 6, max = 12) String nickname, String role
) implements Serializable {

}