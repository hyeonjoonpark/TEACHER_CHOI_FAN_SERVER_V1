package bsm.choi.fancafe.domain.board.repository;

import bsm.choi.fancafe.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query("select b.title, b.content, b.likeCount, b.viewCount, b.writeDate, b.writer " +
            "from Board b " +
            "where b.boardId = :id")
    Object findDetailById(@Param("id") Long id);

    @Query("select b " +
            "from Board b, User u " +
            "where b.writer=u.id and b.writer=:id")
    List<Board> findBoardByUserId(@RequestParam("id") String id);
}
