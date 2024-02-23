package bsm.choi.fancafe.domain.board;

import bsm.choi.fancafe.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
  @Id
  @Column(name = "board_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int boardId;
  @Column(name = "title")
  private String title;
  @Column(name = "content")
  private String content;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public void setUsers(User user) {
    this.user = user;
  }

  @Column(name = "write_date")
  private LocalDateTime writeDate;
  @Column(name = "like_count")
  private int likeCount;
  @Column(name = "view_count")
  private int viewCount;

  @Builder
  public Board(int boardId, String title, String content, User user, LocalDateTime writeDate, int likeCount, int viewCount) {
    this.boardId = boardId;
    this.title = title;
    this.content = content;
    this.user = user;
    this.writeDate = writeDate;
    this.likeCount = likeCount;
    this.viewCount = viewCount;
  }

  public void setLikeCount(int likeCount) {
    this.likeCount = likeCount;
  }
}
