package bsm.choi.fancafe.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@Entity
@Table(name = "board")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardId;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "writer")
    private String writer;
    @Column(name = "write_date")
    private ZonedDateTime writeDate;
    @Column(name = "like_count")
    private int likeCount;
    @Column(name = "view_count")
    private int viewCount;
}
