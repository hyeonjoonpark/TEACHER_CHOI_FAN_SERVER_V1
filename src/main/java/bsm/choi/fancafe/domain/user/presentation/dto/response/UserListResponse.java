package bsm.choi.fancafe.domain.user.presentation.dto.response;

import bsm.choi.fancafe.domain.user.types.GradeType;
import lombok.Builder;

@Builder
public record UserListResponse(
    String name,
    String profileImage,
    String grade
) {

}
