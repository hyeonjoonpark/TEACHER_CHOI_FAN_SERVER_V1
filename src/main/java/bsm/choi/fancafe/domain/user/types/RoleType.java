package bsm.choi.fancafe.domain.user.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    ROLE_USER("일반 사용자"),
    ROLE_ADMIN("관리자");

    private final String userRole;
}
