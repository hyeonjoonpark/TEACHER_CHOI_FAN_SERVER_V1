package bsm.choi.fancafe.domain.user.presentation;

import bsm.choi.fancafe.domain.user.presentation.dto.request.LoginRequestDto;
import bsm.choi.fancafe.domain.user.presentation.dto.request.SignUpRequestDto;
import bsm.choi.fancafe.domain.user.presentation.dto.response.LoginResponseDto;
import bsm.choi.fancafe.domain.user.presentation.dto.response.ResponseDto;
import bsm.choi.fancafe.domain.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto dto) {
        authService.register(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseDto<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        ResponseDto<LoginResponseDto> result = authService.login(loginRequestDto);
        return result;
    }
}
