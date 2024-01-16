package bsm.choi.fancafe.domain.board.service;

import bsm.choi.fancafe.domain.board.entity.BoardEntity;
import bsm.choi.fancafe.domain.board.presentation.dto.request.BoardUploadRequestDto;
import bsm.choi.fancafe.domain.board.repository.BoardRepository;
import bsm.choi.fancafe.domain.user.entity.UserEntity;
import bsm.choi.fancafe.global.exception.ErrorCode.ErrorCode;
import bsm.choi.fancafe.global.exception.GlobalException;
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

    @Transactional
    public void save(BoardUploadRequestDto dto) {
        String title = dto.getTitle();
        String content = dto.getContent();
        String writer = dto.getId();

        try {
            BoardEntity board = dto.toEntity(title, content, writer);
            boardRepository.save(board);
        } catch(GlobalException e) {
            throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
