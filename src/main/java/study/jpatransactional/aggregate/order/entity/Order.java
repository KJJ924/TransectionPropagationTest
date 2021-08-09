package study.jpatransactional.aggregate.order.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private final List<OrderItem> orderItems = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createAt;

    @Builder
    private Order(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
    }
}
