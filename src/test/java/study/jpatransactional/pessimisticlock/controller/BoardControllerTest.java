package study.jpatransactional.pessimisticlock.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.web.servlet.MockMvc;
import study.jpatransactional.pessimisticlock.dao.BoardRepository;
import study.jpatransactional.pessimisticlock.dto.RequestBoard;
import study.jpatransactional.pessimisticlock.entity.Board;
import study.jpatransactional.pessimisticlock.event.BoardCount;
import study.jpatransactional.pessimisticlock.event.BoardCountRepository;
import study.jpatransactional.pessimisticlock.service.BoardService;

/**
 * @author dkansk924@naver.com
 * @since 2021/05/23
 */

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardService boardService;

    @Autowired
    BoardCountRepository boardCountRepository;

    @BeforeEach
    void before() {
        RequestBoard requestBoard = new RequestBoard();
        requestBoard.setTitle("Test");
        boardService.save(requestBoard);
    }

    @Test
    @DisplayName("Board 요청을 n 번 호출 하는경우 조회수는 n 이 되어야함.")
    void getBoard() throws InterruptedException {
        //given
        ExecutorService service = Executors.newFixedThreadPool(10);
        //when
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                try {
                    Board board = boardService.getBoard(1L);
                } catch (ObjectOptimisticLockingFailureException lockingFailureException) {
                    System.out.println("충돌 !");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(1000);
        //then

        BoardCount board = boardCountRepository.findById(1L).orElseThrow(RuntimeException::new);
        assertThat(board.getCount()).isEqualTo(10);
    }
}