package study.jpatransactional.pessimisticlock.controller;



import static study.jpatransactional.pessimisticlock.controller.BoardController.ROOT;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.jpatransactional.pessimisticlock.dto.RequestBoard;
import study.jpatransactional.pessimisticlock.entity.Board;
import study.jpatransactional.pessimisticlock.service.BoardService;

/**
 * @author dkansk924@naver.com
 * @since 2021/05/23
 */


@RestController
@RequestMapping(ROOT)
@RequiredArgsConstructor
public class BoardController {

    public static final String ROOT = "/boards";
    private final BoardService boardService;

    // Test 위한 프로젝트임으로 별도의 응답 DTO 는 생성하지 않음
    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable Long id){
        Board board = boardService.getBoard(id);
        return ResponseEntity.ok(board);
    }


    @PostMapping
    public ResponseEntity<Board> saveBoard(@RequestBody RequestBoard requestBoard){
        Board board = boardService.save(requestBoard);

        return ResponseEntity.status(HttpStatus.CREATED)
            .header("Location",ROOT+"/"+board.getId())
            .body(board);
    }
}
