package bsm.choi.fancafe.domain.auth.presentation;

import bsm.choi.fancafe.domain.auth.presentation.dto.request.LoginRequest;
import bsm.choi.fancafe.domain.user.presentation.dto.request.SignUpRequestDto;
import bsm.choi.fancafe.domain.user.presentation.dto.response.LoginResponseDto;
import bsm.choi.fancafe.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/signUp")
  public void signUp(@RequestBody SignUpRequestDto dto) {
    authService.register(dto);
  }

  @PostMapping("/login")
  public LoginResponseDto login(@RequestBody LoginRequest dto) {
    LoginResponseDto result = authService.login(dto);
    return result;
  }
}
