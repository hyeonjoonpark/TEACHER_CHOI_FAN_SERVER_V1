package bsm.choi.fancafe.domain.board.presentation.dto.response;

import bsm.choi.fancafe.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardListResponseDto {
  private int boardId;
  private String title;
  private String userId;
  private LocalDateTime writeDate;

  public static BoardListResponseDto of(Board board) {
    return new BoardListResponseDto(
      board.getBoardId(),
      board.getTitle(),
      board.getWriter().getId(),
      board.getWriteDate()
    );
  }
}
