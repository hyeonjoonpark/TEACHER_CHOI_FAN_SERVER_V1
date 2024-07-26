package bsm.choi.fancafe.domain.user.presentation;

import bsm.choi.fancafe.domain.user.presentation.dto.request.UserUpdateRequest;
import bsm.choi.fancafe.domain.user.presentation.dto.response.UserDetailResponse;
import bsm.choi.fancafe.domain.user.presentation.dto.response.UserListResponse;
import bsm.choi.fancafe.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/read-all")
    public List<UserListResponse> allUsers() {
        return userService.readAll();
    }

    @GetMapping("/read/:userEmail")
    public UserDetailResponse readUser(@RequestParam String userEmail, HttpServletRequest request) {
        return userService.read(userEmail, request);
    }

    @PutMapping("/update/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserUpdateRequest dto, HttpServletRequest request) {
        userService.update(dto, request);
        return ResponseEntity.status(HttpStatus.OK).body("프로필 수정이 정상적으로 완료되었습니다");
    }
}
