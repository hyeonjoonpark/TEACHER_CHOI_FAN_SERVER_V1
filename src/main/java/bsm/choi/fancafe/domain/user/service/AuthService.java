package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.user.entity.UserEntity;
import bsm.choi.fancafe.domain.user.presentation.dto.request.LoginRequestDto;
import bsm.choi.fancafe.domain.user.presentation.dto.response.LoginResponseDto;
import bsm.choi.fancafe.domain.user.presentation.dto.response.ResponseDto;
import bsm.choi.fancafe.domain.user.provider.TokenProvider;
import bsm.choi.fancafe.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Autowired
    public AuthService(UserRepository userRepository, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    public ResponseDto<LoginResponseDto> login(LoginRequestDto dto) {
        String id = dto.getId();
        String email = dto.getEmail();

        try {
            boolean exist = userRepository.existsById(id);
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
            int exprTime = 1000 * 60 * 60;
            System.out.println(exprTime);

            String token = tokenProvider.createJwt(id);
            System.out.println(token);

            LoginResponseDto loginResponseDto = new LoginResponseDto(token, exprTime, userEntity);
            System.out.println(loginResponseDto);
            return ResponseDto.setSuccess("Login Success", loginResponseDto);
        } catch (Exception e) {
            e.printStackTrace(); // 에러 스택 트레이스를 출력합니다.
            return ResponseDto.setFailed("Database Error");
        }
    }
}
