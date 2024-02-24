package bsm.choi.fancafe.domain.board.presentation;

import bsm.choi.fancafe.domain.board.Board;
import bsm.choi.fancafe.domain.board.presentation.dto.request.BoardUploadRequestDto;
import bsm.choi.fancafe.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/list")
  public Page<Board> boardList(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size
  ) {
    int adjustedPage = page > 0 ? page - 1 : 0; // 페이지 번호 조정
    PageRequest pageRequest = PageRequest.of(adjustedPage, size);
    return boardService.boardList(pageRequest);
  }

  @GetMapping("/detail")
  public Object boardDetail(@RequestParam Long id) {
    Object board = boardService.getDetail(id);
    return board;
  }

  @PutMapping("/like/update")
  public ResponseEntity<Board> updateCount(@RequestBody int likeCount) {
    boardService.update(likeCount);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/upload")
  public ResponseEntity<Object> boardUpload(@RequestBody BoardUploadRequestDto dto) {
    boardService.save(dto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/list/:id")
  public List<Board> boardList(@RequestParam String id) {
    List<Board> result = boardService.getBoardList(id);
    return result;
  }
}
