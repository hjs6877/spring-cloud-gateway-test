package com.itvillage.orders;

import com.itvillage.delivery.Delivery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import reactor.util.function.Tuple2;

public record OrderDetailsResponse(Order order, Delivery delivery) {
    public static OrderDetailsResponse from(Tuple2<Order, Delivery> tuple) {
        return new OrderDetailsResponse(tuple.getT1(), tuple.getT2());
    }
}
