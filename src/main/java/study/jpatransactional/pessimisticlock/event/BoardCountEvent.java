package study.jpatransactional.pessimisticlock.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author dkansk924@naver.com
 * @since 2021/05/23
 */

@Getter
@NoArgsConstructor
public class BoardCountEvent {

    private Long BoardId;

    public BoardCountEvent(Long boardId) {
        BoardId = boardId;
    }
}
