package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.user.entity.UserEntity;
import bsm.choi.fancafe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public List<UserEntity> userList(String id) {
        List<UserEntity> result = userRepository.findAllByIdNot(id);
        return result;
    }
}
