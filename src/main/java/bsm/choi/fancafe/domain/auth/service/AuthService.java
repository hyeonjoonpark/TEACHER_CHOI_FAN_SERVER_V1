package bsm.choi.fancafe.domain.auth.service;

import bsm.choi.fancafe.domain.auth.presentation.dto.request.LoginRequest;
import bsm.choi.fancafe.domain.auth.presentation.dto.request.SignUpRequest;
import bsm.choi.fancafe.domain.auth.presentation.dto.response.LoginResponse;
import bsm.choi.fancafe.domain.auth.utils.JwtUtil;
import bsm.choi.fancafe.domain.user.User;
import bsm.choi.fancafe.domain.user.details.CustomUserDetailService;
import bsm.choi.fancafe.domain.user.details.CustomUserDetails;
import bsm.choi.fancafe.domain.user.repository.UserRepository;
import bsm.choi.fancafe.global.exception.ErrorCode.ErrorCode;
import bsm.choi.fancafe.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;

  @Value("${jwt.secret}")
  private String secretKey;

  private final Long ACCESS_TOKEN_EXPIRED_TIME = 1000 * 60 * 60L;
  private final Long REFRESH_TOKEN_EXPIRED_TIME = 1000 * 60 * 60 * 24 * 7L;

  private final PasswordEncoder passwordEncoder;
  private final CustomUserDetailService userDetailService;
  private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@gmail.com";
  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  public static boolean validate(String email) {
    return pattern.matcher(email).matches();
  }

  @Transactional
  public void register(SignUpRequest dto) throws GlobalException {

  }

  @Transactional
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

    return null;
  }
}
