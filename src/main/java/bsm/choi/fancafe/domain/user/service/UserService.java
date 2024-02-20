package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
public class UserService {
    private final UserRepository userRepository;
    @Transactional(readOnly = true)
    public Optional<Object[]> getInfo(String id) {
        Optional<Object[]> result = userRepository.findUserById(id);
        return result;
    }
}
