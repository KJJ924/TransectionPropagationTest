package study.jpatransactional.aggregate.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.jpatransactional.aggregate.order.entity.Order;

/**
 * @author dkansk924@naver.com
 * @since 2021/08/09
 */

public interface OrderRepository extends JpaRepository<Order, Long> {

}
