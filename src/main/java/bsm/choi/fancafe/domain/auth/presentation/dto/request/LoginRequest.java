package bsm.choi.fancafe.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Builder
public record LoginRequest(
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "유효한 Gmail 주소를 입력하세요.") // gmail 정규표현식
        String email,
        String password
) {
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
