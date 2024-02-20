package bsm.choi.fancafe.domain.board;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Getter @ToString
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
    public Board(int boardId, String title, String content, String writer, LocalDateTime writeDate, int likeCount, int viewCount) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.writeDate = writeDate;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
