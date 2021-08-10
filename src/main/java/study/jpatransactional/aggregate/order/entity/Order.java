package study.jpatransactional.aggregate.order.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @author dkansk924@naver.com
 * @since 2021/08/09
 */

@Entity(name = "Orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private String orderNumber;

    private final OrderItemList itemList = new OrderItemList();

    @CreationTimestamp
    private LocalDateTime createAt;

    @Builder
    private Order(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void addOrderItem(OrderItem orderItem){
       itemList.addOrderItem(orderItem);
    }

    public int totalPrice(){
        return itemList.getTotalPrice();
    }
    public String menuNameList(){
        return itemList.menuNameList();
    }
}
