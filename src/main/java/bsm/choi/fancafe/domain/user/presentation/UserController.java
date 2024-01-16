package bsm.choi.fancafe.domain.user.presentation;

import bsm.choi.fancafe.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/info")
    public Optional<Object[]> userInfo(@RequestParam String id) {
        Optional<Object[]> result = userService.getInfo(id);
        return result;
    }
}
