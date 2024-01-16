package bsm.choi.fancafe.domain.board.presentation.dto.request;

import bsm.choi.fancafe.domain.board.entity.BoardEntity;
import bsm.choi.fancafe.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardUploadRequestDto {
    private String title;
    private String content;
    private String id;

    public BoardEntity toEntity(String title, String content, String id) {
        return BoardEntity.builder()
                .title(title)
                .content(content)
                .writer(id)
                .viewCount(0)
                .likeCount(0)
                .writeDate(LocalDateTime.now())
                .build();
    }
}
