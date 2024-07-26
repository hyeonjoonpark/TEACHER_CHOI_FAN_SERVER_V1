package bsm.choi.fancafe.domain.user.presentation;

import bsm.choi.fancafe.domain.user.presentation.dto.response.UserListResponse;
import bsm.choi.fancafe.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/all")
    public List<UserListResponse> allUsers() {
        return userService.readAll();
    }
}
