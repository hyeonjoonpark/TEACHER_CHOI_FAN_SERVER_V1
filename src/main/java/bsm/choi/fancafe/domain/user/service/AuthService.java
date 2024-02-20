package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.user.User;
import bsm.choi.fancafe.domain.user.presentation.dto.request.LoginRequestDto;
import bsm.choi.fancafe.domain.user.presentation.dto.request.SignUpRequestDto;
import bsm.choi.fancafe.domain.user.presentation.dto.response.LoginResponseDto;
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
  private final JwtUtil jwtUtil;

  @Value("${jwt.secret}")
  private String secretKey;

  private Long exprTime = 1000 * 60 * 60L;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@gmail.com";
  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  public static boolean validate(String email) {
    return pattern.matcher(email).matches();
  }

  public void register(SignUpRequestDto dto) throws GlobalException {
    try {
      String email = dto.getEmail();
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

  public LoginResponseDto login(LoginRequestDto dto) throws GlobalException {
    String id = dto.getId();
    String email = dto.getEmail();

    try {
      if (id == null || email == null) {
        throw new GlobalException(ErrorCode.BAD_REQUEST_AUTH);
      }

      String token = jwtUtil.createJwt(id, email, secretKey, exprTime);

      return new LoginResponseDto(token, id);

    } catch (GlobalException e) {
      throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }
}
