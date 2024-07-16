package bsm.choi.fancafe.domain.board.presentation.dto.request;

import bsm.choi.fancafe.domain.board.Board;

public record BoardUploadRequest(
  String title,
  String content,
  String writerId
) {
  public Board toEntity() {
    return Board.builder()
      .title(title)
      .content(content)
      .build();
  }
}
