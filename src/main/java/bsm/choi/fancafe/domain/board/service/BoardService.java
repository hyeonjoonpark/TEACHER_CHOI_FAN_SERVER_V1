package bsm.choi.fancafe.domain.board.service;

import bsm.choi.fancafe.domain.board.entity.BoardEntity;
import bsm.choi.fancafe.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Page<BoardEntity> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
}
