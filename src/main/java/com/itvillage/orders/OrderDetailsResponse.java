package com.itvillage.orders;

import com.itvillage.delivery.Delivery;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDetailsResponse {
    private Order order;
    private Delivery delivery;
}
