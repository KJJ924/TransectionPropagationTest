package study.jpatransactional.aggregate.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import study.jpatransactional.aggregate.order.entity.Item;
import study.jpatransactional.aggregate.order.entity.Order;
import study.jpatransactional.aggregate.order.entity.OrderItem;
import study.jpatransactional.aggregate.order.repository.OrderRepository;

/**
 * @author dkansk924@naver.com
 * @since 2021/08/09
 */

@Component
@RequiredArgsConstructor
public class AggregateRootCascadeTestRunner implements ApplicationRunner {

    private final OrderRepository orderRepository;


    //Query 발생 갯수, 영속성 전이 확인
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Order order = Order.builder().orderNumber("A1234").build();

        Item item = Item.builder()
            .name("testItem")
            .price(10000)
            .build();
        OrderItem orderItem = OrderItem.builder().item(item).build();
        order.addOrderItem(orderItem);
        orderRepository.save(order);
    }
}

