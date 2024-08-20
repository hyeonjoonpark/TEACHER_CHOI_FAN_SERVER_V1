package bsm.choi.fancafe.domain.auth.presentation;

import bsm.choi.fancafe.domain.auth.presentation.dto.request.LoginRequest;
import bsm.choi.fancafe.domain.auth.presentation.dto.response.LoginResponse;
import bsm.choi.fancafe.domain.auth.service.AuthService;
import bsm.choi.fancafe.domain.auth.presentation.dto.request.SignUpRequest;
import bsm.choi.fancafe.domain.user.presentation.dto.request.UserRoleUpdateRequest;
import bsm.choi.fancafe.domain.user.types.RoleType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @ModelAttribute SignUpRequest dto) {
        authService.register(dto);
        return ResponseEntity.status(HttpStatus.OK).body("성공적으로 회원가입 되었습니다");
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest dto) {
        return authService.login(dto);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader("REFRESH_TOKEN") String refreshToken) {
        String newAccessToken = authService.refresh(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).body(newAccessToken);
    }

    @PostMapping("/add/user_role")
    public ResponseEntity<?> addUserRole(@RequestBody UserRoleUpdateRequest dto) {
        authService.addRole(dto);
        return ResponseEntity.status(HttpStatus.OK).body(dto.role() + " " + "권한을 추가하였습니다");
    }
}
