package study.jpatransactional.aggregate.order.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author dkansk924@naver.com
 * @since 2021/08/11
 */


class OrderEntityTest {

    @Test
    @DisplayName("현재 주문의 메뉴 전체의 이름을 반환해야한다.")
    void orderGetItemNameList() {
        //given
        OrderItem item = OrderItem.builder().item(new Item("item", 1000)).build();
        OrderItem item2 = OrderItem.builder().item(new Item("item2", 1000)).build();
        Order order = Order.builder().orderNumber("ABCDE123").build();
        order.addOrderItem(item);
        order.addOrderItem(item2);
        //when
        String menuNames = order.menuNameList();
        //then
        assertThat(menuNames).isEqualTo("item,item2");
    }


    @Test
    @DisplayName("현재 주문의 전체 금액을 반환해야한다.")
    void orderGetTotalPrice() {
        //given
        OrderItem item = OrderItem.builder().item(new Item("item", 1000)).build();
        OrderItem item2 = OrderItem.builder().item(new Item("item2", 1000)).build();
        Order order = Order.builder().orderNumber("ABCDE123").build();
        order.addOrderItem(item);
        order.addOrderItem(item2);
        //when
        int totalPrice = order.totalPrice();
        //then
        assertThat(totalPrice).isEqualTo(2000);
    }
}