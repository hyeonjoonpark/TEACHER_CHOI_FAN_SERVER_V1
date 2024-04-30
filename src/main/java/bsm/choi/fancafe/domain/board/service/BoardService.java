package bsm.choi.fancafe.domain.board.service;

import bsm.choi.fancafe.domain.board.Board;
import bsm.choi.fancafe.domain.board.presentation.dto.request.BoardUploadRequest;
import bsm.choi.fancafe.domain.board.presentation.dto.response.BoardListResponse;
import bsm.choi.fancafe.domain.board.repository.BoardRepository;
import bsm.choi.fancafe.domain.user.User;
import bsm.choi.fancafe.domain.user.repository.UserRepository;
import bsm.choi.fancafe.global.exception.ErrorCode.ErrorCode;
import bsm.choi.fancafe.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public Page<BoardListResponse> boardList(Pageable pageable) {
    try {
      Page<Board> boardPage =  boardRepository.findAll(pageable);

      List<BoardListResponse> boardListResponseDtos = boardPage.stream()
        .map(BoardListResponse::of)
        .collect(Collectors.toList());
      return new PageImpl<>(boardListResponseDtos, pageable, boardPage.getTotalElements());
    } catch (GlobalException e) {
      throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  @Transactional(readOnly = true)
  public Object getDetail(Long id) {
    return boardRepository.findDetailById(id);
  }


  @Transactional
  public void save(BoardUploadRequest dto) {
    try {
      Board board = dto.toEntity();
      User user = userRepository.findById(dto.writerId())
        .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

      board.setWriter(user); // User 객체를 Board에 설정
      user.addBoard(board); // User의 게시글 목록에 Board를 추가

      boardRepository.save(board);
    } catch (GlobalException e) {
      throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  @Transactional
  public void update(int boardId) {
    try {
      Optional<Board> optionalBoard = boardRepository.findById(boardId);
      if (optionalBoard.isPresent()) {
        Board board = optionalBoard.get();
        board.setLikeCount(board.getLikeCount() + 1);
        boardRepository.save(board);
      }
    } catch (GlobalException e) {
      throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  @Transactional(readOnly = true)
  public List<Board> getBoardList(String id) {
    return boardRepository.findBoardByUserId(id);
  }
}
