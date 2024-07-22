package bsm.choi.fancafe.domain.board.presentation.dto.response;

import bsm.choi.fancafe.domain.board.Board;

import java.time.LocalDateTime;
import java.util.UUID;


public record BoardListResponse(
  Long boardId,
  String title,
  UUID userId,
  LocalDateTime writeDate
) {
  public static BoardListResponse of(Board board) {
    return new BoardListResponse(
      board.getBoardId(),
      board.getTitle(),
      board.getWriter().getUuid(),
      board.getWriteDate()
    );
  }
}
