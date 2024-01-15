package bsm.choi.fancafe.domain.user.presentation.dto.request;

import bsm.choi.fancafe.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    private String id;
    private String email;
    private String password;

    public UserEntity toEntity(BCryptPasswordEncoder bCryptPasswordEncoder) {
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        return UserEntity.builder()
                .id(id)
                .email(email)
                .password(encodedPassword)
                .build();
    }
}
