package bsm.choi.fancafe.domain.auth.service;

import bsm.choi.fancafe.domain.auth.presentation.dto.request.LoginRequest;
import bsm.choi.fancafe.domain.auth.presentation.dto.request.SignUpRequest;
import bsm.choi.fancafe.domain.auth.presentation.dto.response.LoginResponse;
import bsm.choi.fancafe.domain.auth.utils.JwtUtil;
import bsm.choi.fancafe.domain.user.User;
import bsm.choi.fancafe.domain.user.details.CustomUserDetailService;
import bsm.choi.fancafe.domain.user.presentation.dto.request.UserRoleUpdateRequest;
import bsm.choi.fancafe.domain.user.repository.UserRepository;
import bsm.choi.fancafe.domain.user.types.RoleType;
import bsm.choi.fancafe.global.exception.ErrorCode.ErrorCode;
import bsm.choi.fancafe.global.exception.GlobalException;
import bsm.choi.fancafe.global.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final FileUtil fileUtil;

    @Value("${jwt.secret}")
    private String secretKey;

    private static final Long ACCESS_TOKEN_EXPIRED_TIME = 1000 * 60 * 60L;
    private static final Long REFRESH_TOKEN_EXPIRED_TIME = 1000 * 60 * 60 * 24 * 7L;

    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailService userDetailService;
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@gmail.com$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean validate(String email) {
        return pattern.matcher(email).matches();
    }

    @Transactional(rollbackFor = Exception.class)
    public void register(SignUpRequest dto) throws GlobalException {
        String email = dto.email();

        if (userRepository.findByEmail(email).isPresent()) {
            throw new GlobalException(ErrorCode.USER_ALREADY_EXIST);
        }

        if (!validate(email)) {
            throw new GlobalException(ErrorCode.BAD_REQUEST_AUTH);
        }

        String profilePictureUrl = fileUtil.handleProfileImage(dto.profileImage());

        User user = dto.toEntity(passwordEncoder, profilePictureUrl);
        userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public LoginResponse login(LoginRequest dto) throws GlobalException {
        String email = dto.email();
        String password = dto.password();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new GlobalException(ErrorCode.BAD_REQUEST_AUTH);
        }

        String accessToken = JwtUtil.createJwt(user.getUuid(), secretKey, ACCESS_TOKEN_EXPIRED_TIME);
        String refreshToken = JwtUtil.createJwt(user.getUuid(), secretKey, REFRESH_TOKEN_EXPIRED_TIME);

        return LoginResponse.of(
                "성공적으로 로그인 되었습니다",
                HttpStatus.OK,
                email,
                accessToken,
                refreshToken
        );
    }

    // AccessToken 재발급
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public String refresh(String refreshToken) {
        return null;
    }

    // 권한 추가
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void addRole(UserRoleUpdateRequest dto) {
        User user = userRepository.findByNickname(dto.nickname())
                .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

        RoleType newRole =
                dto.role().equals("관리자") ?
                        RoleType.ROLE_ADMIN :
                        RoleType.ROLE_USER;

        user.addRole(newRole);
        userRepository.save(user);
    }
}
