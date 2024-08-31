package com.itvillage.orders;

import lombok.Getter;

@Getter
public class Order {
    private Long orderId;
    private OrderState orderState;

    @Getter
    public enum OrderState {
        ORDER_RECEIVED("주문 접수"),
        PREPARING("조리 중"),
        OUT_FOR_DELIVERY("배달 중"),
        DELIVERED("배달 완료");

        private final String orderState;

        OrderState(String orderState) {
            this.orderState = orderState;
        }
    }
}
