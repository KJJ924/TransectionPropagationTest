package study.jpatransactional.pessimisticlock.event;

import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dkansk924@naver.com
 * @since 2021/05/23
 */

public interface BoardCountRepository extends JpaRepository<BoardCount, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from BoardCount c where c.boardId = :boardId")
    BoardCount findByBoardId(Long boardId);
}
