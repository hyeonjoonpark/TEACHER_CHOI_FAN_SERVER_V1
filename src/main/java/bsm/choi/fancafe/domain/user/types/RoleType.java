package bsm.choi.fancafe.domain.user.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    ROLE_USER("일반 사용자"),
    ROLE_ADMIN("관리자"),
    ROLE_ANONYMOUS("권한이 없는 익명의 사용자");

    private final String userRole;
}
