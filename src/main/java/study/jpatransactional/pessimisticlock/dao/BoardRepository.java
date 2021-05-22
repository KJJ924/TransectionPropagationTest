package study.jpatransactional.pessimisticlock.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpatransactional.pessimisticlock.entity.Board;

/**
 * @author dkansk924@naver.com
 * @since 2021/05/23
 */


public interface BoardRepository extends JpaRepository<Board,Long> {

}
