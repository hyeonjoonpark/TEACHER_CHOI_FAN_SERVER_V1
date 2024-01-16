package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.user.entity.UserEntity;
import bsm.choi.fancafe.domain.user.presentation.dto.request.LoginRequestDto;
import bsm.choi.fancafe.domain.user.presentation.dto.request.SignUpRequestDto;
import bsm.choi.fancafe.domain.user.presentation.dto.response.LoginResponseDto;
import bsm.choi.fancafe.domain.user.presentation.dto.response.ResponseDto;
import bsm.choi.fancafe.domain.user.provider.TokenProvider;
import bsm.choi.fancafe.domain.user.repository.UserRepository;
import bsm.choi.fancafe.global.exception.ErrorCode.ErrorCode;
import bsm.choi.fancafe.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@gmail.com";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean validate(String email) {
        return pattern.matcher(email).matches();
    }

    public void register(SignUpRequestDto dto) throws GlobalException{
        try {
            String email = dto.getEmail();
            boolean isEmailRight = validate(email);

            UserEntity isExist = userRepository.findByEmail(email);

            if(isExist != null) {
                throw new GlobalException(ErrorCode.USER_ALREADY_EXIST);
            }

            if(isEmailRight) {
                UserEntity user = dto.toEntity(bCryptPasswordEncoder);
                userRepository.save(user);
            }
            else {
                throw new GlobalException(ErrorCode.BAD_REQUEST_AUTH);
            }
        } catch (GlobalException e) {
            e.printStackTrace();
            throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseDto<LoginResponseDto> login(LoginRequestDto dto) {
        String id = dto.getId();
        String email = dto.getEmail();

        try {
            boolean exist = userRepository.existsByIdAndEmail(id, email);
            System.out.println(exist);
            if (!exist) {
                return ResponseDto.setFailed("Login Info is Wrong");
            }

            Optional<UserEntity> userEntityOptional = userRepository.findById(id);
            System.out.println(userEntityOptional);
            System.out.println(userEntityOptional);

            if (userEntityOptional.isEmpty()) {
                return ResponseDto.setFailed("User not found");
            }

            UserEntity userEntity = userEntityOptional.get();
            int exprTime = (int) tokenProvider.getACCESS_TOKEN_EXPIRE_TIME();
            System.out.println(exprTime);

            String token = tokenProvider.createJwt(id);
            System.out.println(token);

            LoginResponseDto loginResponseDto = new LoginResponseDto(token, exprTime, userEntity);
            System.out.println(loginResponseDto);
            return ResponseDto.setSuccess("Login Success", loginResponseDto);
        } catch (Exception e) {
            return ResponseDto.setFailed("Database Error");
        }
    }
}
