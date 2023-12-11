package bsm.choi.fancafe.domain.user.presentation;

import bsm.choi.fancafe.domain.user.entity.UserEntity;
import bsm.choi.fancafe.domain.user.service.UserListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserListController {

    private final UserListService userListService;

    @GetMapping("/users/list")
    public List<UserEntity> userList(@RequestParam String userId) {
        List<UserEntity> result = userListService.userList(userId);
        return result;
    }
}
