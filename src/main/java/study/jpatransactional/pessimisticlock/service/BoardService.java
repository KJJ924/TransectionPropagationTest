package study.jpatransactional.pessimisticlock.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpatransactional.pessimisticlock.dao.BoardRepository;
import study.jpatransactional.pessimisticlock.dto.RequestBoard;
import study.jpatransactional.pessimisticlock.entity.Board;

/**
 * @author dkansk924@naver.com
 * @since 2021/05/23
 */

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Board getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(RuntimeException::new);
        board.countUpdate();
        return board;
    }

    public Board save(RequestBoard requestBoard) {
        return boardRepository.save(requestBoard.toEntity());
    }
}
