package bsm.choi.fancafe.domain.user.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GradeType {
    LEADER("LEADER"), // 회장
    VIP("VIP"), // VIP
    NEW("NEW"); // 신입

    private final String gradeType;
}
