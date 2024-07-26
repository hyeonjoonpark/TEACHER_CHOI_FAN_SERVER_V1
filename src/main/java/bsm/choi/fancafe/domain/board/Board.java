package bsm.choi.fancafe.domain.board;

import bsm.choi.fancafe.domain.comment.Comment;
import bsm.choi.fancafe.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "content"),
        @Index(columnList = "hash_tag")
})
public class Board {
  @Id @Column(name = "board_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardId;

  @Column(name = "title",length = 100)
  private String title;
  @Column(name = "content")
  private String content;

  @Setter
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @CreatedBy
  private User writer;

  @OneToMany(
          mappedBy = "boardId",
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  private List<Comment> comments;

  public void addComment(Comment comment) {
    comment.setBoardId(this);
    this.comments.add(comment);
  }

  @CreatedDate private LocalDateTime writeDate;

  @Setter @ColumnDefault("0") private int likeCount;
  @Setter @ColumnDefault("0") private int viewCount;

  @Column(name = "hash_tag")
  private String hashTag;

  @Builder
  public Board(String title, String content, String hashTag) {
    this.title = title;
    this.content = content;
    this.hashTag = hashTag;
  }
}
