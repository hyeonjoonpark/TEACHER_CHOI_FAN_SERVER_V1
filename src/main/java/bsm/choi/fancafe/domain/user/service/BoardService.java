package bsm.choi.fancafe.domain.user.service;

import bsm.choi.fancafe.domain.user.entity.BoardEntity;
import bsm.choi.fancafe.domain.user.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<BoardEntity> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
}
