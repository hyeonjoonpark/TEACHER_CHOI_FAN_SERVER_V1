package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.auth.utils.JwtUtil;
import bsm.choi.fancafe.domain.user.User;
import bsm.choi.fancafe.domain.user.presentation.dto.request.UserUpdateRequest;
import bsm.choi.fancafe.domain.user.presentation.dto.response.UserListResponse;
import bsm.choi.fancafe.domain.user.repository.UserRepository;
import bsm.choi.fancafe.domain.user.types.GradeType;
import bsm.choi.fancafe.global.exception.ErrorCode.ErrorCode;
import bsm.choi.fancafe.global.exception.GlobalException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class UserService {
    private final UserRepository userRepository;
    private static final String DEFAULT_PROFILE_IMAGE = "https://www.pngarts.com/files/10/Default-Profile-Picture-PNG-Download-Image.png";

    @Value("${jwt.secret}")
    private String secret;

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

    @Transactional
    public void update(UserUpdateRequest dto) {
        String token = dto.token();
        UUID uuid = JwtUtil.getUserId(token, secret);
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

        user.update(dto.email(), dto.password(), dto.profileImage(), dto.name());
        userRepository.save(user);
    }
}
