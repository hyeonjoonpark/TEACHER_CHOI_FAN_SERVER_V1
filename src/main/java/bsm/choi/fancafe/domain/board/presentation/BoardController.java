package bsm.choi.fancafe.domain.board.presentation;

import bsm.choi.fancafe.domain.board.entity.BoardEntity;
import bsm.choi.fancafe.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public Page<BoardEntity> boardList(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size) {
        int adjustedPage = page > 0 ? page - 1 : 0; // 페이지 번호 조정
        PageRequest pageRequest = PageRequest.of(adjustedPage, size);
        return boardService.boardList(pageRequest);
    }
}
