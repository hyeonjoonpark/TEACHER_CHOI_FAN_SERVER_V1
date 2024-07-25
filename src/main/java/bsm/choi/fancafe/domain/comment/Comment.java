package bsm.choi.fancafe.domain.comment;

import bsm.choi.fancafe.domain.board.Board;
import bsm.choi.fancafe.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_comment_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board boardId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    @CreatedBy
    private User commentWriterId;

    @Length(max = 100, min = 2, message = "댓글은 2자이상 100자 이하여야 합니다")
    private String comment;

    @CreationTimestamp
    private LocalDateTime createTime = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime updateTime = LocalDateTime.now();

    @Builder
    public Comment(Board boardId, User commentWriterId, String comment) {
        this.boardId = boardId;
        this.commentWriterId = commentWriterId;
        this.comment = comment;
    }
}
