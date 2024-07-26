package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.user.User;
import bsm.choi.fancafe.domain.user.presentation.dto.response.UserListResponse;
import bsm.choi.fancafe.domain.user.repository.UserRepository;
import bsm.choi.fancafe.domain.user.types.GradeType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
public class UserService {
    private final UserRepository userRepository;
    private final String DEFAULT_PROFILE_IMAGE = "https://www.pngarts.com/files/10/Default-Profile-Picture-PNG-Download-Image.png";

    @Transactional(readOnly = true)
    public List<UserListResponse> readAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(
                        user -> UserListResponse.builder()
                                .name(user.getName())
                                .profileImage(user.getProfileImage() == null ? DEFAULT_PROFILE_IMAGE : user.getProfileImage())
                                .grade(
                                        user.getGradeType().equals(GradeType.NEW) ? "신입"
                                                : user.getGradeType().equals(GradeType.VIP) ? GradeType.VIP.toString()
                                                : "팬클럽 회장"
                                )
                                .build()
                ).toList();
    }
}
