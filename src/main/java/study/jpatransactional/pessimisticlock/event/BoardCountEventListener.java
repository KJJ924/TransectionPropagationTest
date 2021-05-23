package study.jpatransactional.pessimisticlock.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dkansk924@naver.com
 * @since 2021/05/23
 */
@Component
@RequiredArgsConstructor
@Async
public class BoardCountEventListener {

    private final  BoardCountRepository boardCountRepository;

    @EventListener
    @Transactional
    public void handleBoardCountEvent(BoardCountEvent boardCountEvent){
        BoardCount boardCount =boardCountRepository.findByBoardId(boardCountEvent.getBoardId());
        boardCount.countUp();
    }
}
