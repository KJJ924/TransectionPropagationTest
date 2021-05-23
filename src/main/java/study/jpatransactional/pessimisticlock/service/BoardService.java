package study.jpatransactional.pessimisticlock.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpatransactional.pessimisticlock.dao.BoardRepository;
import study.jpatransactional.pessimisticlock.dto.RequestBoard;
import study.jpatransactional.pessimisticlock.entity.Board;
import study.jpatransactional.pessimisticlock.event.BoardCount;
import study.jpatransactional.pessimisticlock.event.BoardCountEvent;
import study.jpatransactional.pessimisticlock.event.BoardCountRepository;

/**
 * @author dkansk924@naver.com
 * @since 2021/05/23
 */

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardCountRepository boardCountRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Board getBoard(Long id) {
        System.out.println(Thread.currentThread().getName());

        Board board = boardRepository.findById(id).orElseThrow(RuntimeException::new);
        eventPublisher.publishEvent(new BoardCountEvent(board.getId()));
//        Board board = boardRepository.findByIdForUpdate(id);
//        board.countUpdate();
        return board;
    }

    @Transactional
    public Board save(RequestBoard requestBoard) {
        Board board = boardRepository.save(requestBoard.toEntity());
        boardCountRepository.save(new BoardCount(board.getId()));
        return board;
    }
}
