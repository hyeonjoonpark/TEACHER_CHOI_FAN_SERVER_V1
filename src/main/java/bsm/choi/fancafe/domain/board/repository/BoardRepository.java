package bsm.choi.fancafe.domain.board.repository;

import bsm.choi.fancafe.domain.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    @Query("select b.title, b.content, b.likeCount, b.viewCount, b.writeDate, b.writer " +
            "from BoardEntity b " +
            "where b.boardId = :id")
    Object findDetailById(@Param("id") Long id);

}
