package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.user.User;
import bsm.choi.fancafe.domain.user.presentation.dto.request.UserUpdateRequest;
import bsm.choi.fancafe.domain.user.presentation.dto.response.UserDetailResponse;
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

@Service
@RequiredArgsConstructor
@Getter
public class UserService {
    private final UserRepository userRepository;
    private static final String DEFAULT_PROFILE_IMAGE = "https://www.pngarts.com/files/10/Default-Profile-Picture-PNG-Download-Image.png";

    @Value("${jwt.secret}")
    private String secret;

    // 모든 사용자 조회
    @Transactional(
            readOnly = true,
            rollbackFor = Exception.class
    )
    public List<UserListResponse> readAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(
                        user -> UserListResponse.builder()
                                .name(user.getName())
                                .profileImage(user.getProfileImage() == null ? DEFAULT_PROFILE_IMAGE : user.getProfileImage())
                                .grade(
                                        user.getGradeType().equals(GradeType.NEW) ? "신규"
                                                : user.getGradeType().equals(GradeType.VIP) ? GradeType.VIP.toString()
                                                : "팬클럽 회장"
                                )
                                .build()
                ).toList();
    }

    // User 정보 조회
    @Transactional(
            readOnly = true,
            rollbackFor = Exception.class
    )
    public UserDetailResponse read(String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

        return UserDetailResponse.from(user);
    }

    // 프로필 수정
    @Transactional(rollbackFor = Exception.class)
    public void update(UserUpdateRequest dto) {
        String email = dto.email();
        String password = dto.password();
        String name = dto.name();
        String profileImage = dto.profileImage();
        String nickname = dto.nickname();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

        user.updateProfile(email, password, profileImage, name, nickname);

        userRepository.save(user);
    }
}
