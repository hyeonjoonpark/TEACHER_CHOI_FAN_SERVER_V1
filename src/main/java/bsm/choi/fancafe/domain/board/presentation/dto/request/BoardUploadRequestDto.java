package bsm.choi.fancafe.domain.board.presentation.dto.request;

import bsm.choi.fancafe.domain.board.Board;
import bsm.choi.fancafe.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardUploadRequestDto {
  private String title;
  private String content;
  private User writerId;

  public Board toEntity() {
    return Board.builder()
      .title(title)
      .content(content)
      .writer(writerId)
      .viewCount(0)
      .likeCount(0)
      .writeDate(LocalDateTime.now())
      .build();
  }
}
