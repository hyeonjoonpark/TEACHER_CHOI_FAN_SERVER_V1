package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.user.User;
import bsm.choi.fancafe.domain.user.presentation.dto.request.LoginRequest;
import bsm.choi.fancafe.domain.user.presentation.dto.request.SignUpRequest;
import bsm.choi.fancafe.domain.user.presentation.dto.response.LoginResponse;
import bsm.choi.fancafe.domain.user.utils.JwtUtil;
import bsm.choi.fancafe.domain.user.repository.UserRepository;
import bsm.choi.fancafe.global.exception.ErrorCode.ErrorCode;
import bsm.choi.fancafe.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;

  @Value("${jwt.secret}")
  private String secretKey;

  private final Long exprTime = 1000 * 60 * 60L;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@gmail.com";
  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  public static boolean validate(String email) {
    return pattern.matcher(email).matches();
  }

  public void register(SignUpRequest dto) throws GlobalException {
    try {
      String email = dto.email();
      boolean isEmailRight = validate(email);

      User isExist = userRepository.findByEmail(email);

      if (isExist != null) {
        throw new GlobalException(ErrorCode.USER_ALREADY_EXIST);
      }

      if (!isEmailRight) {
        throw new GlobalException(ErrorCode.BAD_REQUEST_AUTH);

      }

      User user = dto.toEntity(bCryptPasswordEncoder);
      userRepository.save(user);
    } catch (GlobalException e) {
      throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  public LoginResponse login(LoginRequest dto) throws GlobalException {
    String id = dto.id();
    String email = dto.email();

    try {
      if (id == null || email == null) {
        throw new GlobalException(ErrorCode.BAD_REQUEST_AUTH);
      }

      String token = JwtUtil.createJwt(id, email, secretKey, exprTime);

      return LoginResponse.builder()
        .id(id)
        .email(email)
        .token(token)
        .build();

    } catch (GlobalException e) {
      throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }
}
