package com.itvillage.orders;

import lombok.Getter;

@Getter
public class Order {
    private Long orderId;
    private OrderState orderState;

    @Getter
    public enum OrderState {
        PENDING("주문 대기 중"),
        CONFIRMED("주문 확인"),
        PREPARING("음식 준비 중"),
        READY_FOR_PICKUP("픽업 준비 완료"), // 픽업 준비 완료
        DELIVERING("배달 중"),
        COMPLETED("주문 완료"),
        CANCELED ("주문 취소됨");

        private final String orderState;

        OrderState(String orderState) {
            this.orderState = orderState;
        }
    }
}
