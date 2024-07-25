package bsm.choi.fancafe.domain.board;

import bsm.choi.fancafe.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
//@Table(indexes = {
//        @Index(columnList = "title"),
//        @Index(columnList = "content"),
//        @Index(columnList = "hashTag")
//})
public class Board {
  @Id @Column(name = "board_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardId;

  @Column(length = 100)
  private String title;
  @Column(length = 1000)
  private String content;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @CreatedBy
  private User writer;

  @CreatedDate private LocalDateTime writeDate;

  @Setter @ColumnDefault("0") private int likeCount;
  @Setter @ColumnDefault("0") private int viewCount;

  private String hashTag;

  @Builder
  public Board(String title, String content, String hashTag) {
    this.title = title;
    this.content = content;
    this.hashTag = hashTag;
  }
}
