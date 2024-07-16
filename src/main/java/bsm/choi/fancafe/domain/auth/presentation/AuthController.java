package bsm.choi.fancafe.domain.auth.presentation;

import bsm.choi.fancafe.domain.auth.presentation.dto.request.LoginRequest;
import bsm.choi.fancafe.domain.auth.presentation.dto.response.LoginResponse;
import bsm.choi.fancafe.domain.auth.service.AuthService;
import bsm.choi.fancafe.domain.user.presentation.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/signUp")
  public void signUp(@RequestBody SignUpRequest dto) {

  }

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest dto) {
    return null;
  }
}
