package bsm.choi.fancafe.domain.board.entity;

import bsm.choi.fancafe.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardId;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id")
    private String writer;
    @Column(name = "write_date")
    private LocalDateTime writeDate;
    @Column(name = "like_count")
    private int likeCount;
    @Column(name = "view_count")
    private int viewCount;

    @Builder
    public BoardEntity(int boardId, String title, String content, String writer, LocalDateTime writeDate, int likeCount, int viewCount) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.writeDate = writeDate;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
    }
}
