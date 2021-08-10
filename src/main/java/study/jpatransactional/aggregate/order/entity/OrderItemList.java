package study.jpatransactional.aggregate.order.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * @author dkansk924@naver.com
 * @since 2021/08/10
 */

@Embeddable
public class OrderItemList {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private final List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public int getTotalPrice() {
        return orderItems.stream()
            .mapToInt(o -> o.getItem().getPrice())
            .sum();
    }

    public String menuNameList() {
        return orderItems.stream()
            .map(o -> o.getItem().getName())
            .collect(Collectors.joining(","));
    }
}
