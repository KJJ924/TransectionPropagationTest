package study.jpatransactional.pessimisticlock.dao;

import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import study.jpatransactional.pessimisticlock.entity.Board;

/**
 * @author dkansk924@naver.com
 * @since 2021/05/23
 */


public interface BoardRepository extends JpaRepository<Board, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from Board b where b.id = :id")
    Board findByIdForUpdate(Long id);
}
