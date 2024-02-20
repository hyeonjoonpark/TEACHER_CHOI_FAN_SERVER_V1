package bsm.choi.fancafe.domain.board.presentation.dto.request;

import bsm.choi.fancafe.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardUploadRequestDto {
    private String title;
    private String content;
    private String id;

    public Board toEntity(String title, String content, String id) {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(id)
                .viewCount(0)
                .likeCount(0)
                .writeDate(LocalDateTime.now())
                .build();
    }
}
