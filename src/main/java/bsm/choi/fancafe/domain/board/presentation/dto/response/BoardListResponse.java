package bsm.choi.fancafe.domain.board.presentation.dto.response;

import bsm.choi.fancafe.domain.board.Board;

import java.time.LocalDateTime;


public record BoardListResponse(
  int boardId,
  String title,
  String userId,
  LocalDateTime writeDate
) {
  public static BoardListResponse of(Board board) {
    return new BoardListResponse(
      board.getBoardId(),
      board.getTitle(),
      board.getWriter().getId(),
      board.getWriteDate()
    );
  }
}
