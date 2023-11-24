package bsm.choi.fancafe.domain.user.presentation.dto.response;

import bsm.choi.fancafe.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
    private int exprTime;
    private UserEntity user;
}
