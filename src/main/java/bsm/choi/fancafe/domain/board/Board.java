package bsm.choi.fancafe.domain.board;

import bsm.choi.fancafe.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Board {
  @Id @Column(name = "board_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardId;

  private String title;
  private String content;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @CreatedBy
  private User writer;

  @CreatedDate private LocalDateTime writeDate;

  @Setter private int likeCount;
  @Setter private int viewCount;

  @Builder
  public Board(String title, String content, int likeCount, int viewCount) {
    this.title = title;
    this.content = content;
    this.likeCount = likeCount;
    this.viewCount = viewCount;
  }
}
