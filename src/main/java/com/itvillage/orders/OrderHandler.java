package com.itvillage.orders;

import com.itvillage.delivery.Delivery;
import com.itvillage.delivery.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.EntityResponse.fromObject;

@Slf4j
@Component
public class OrderHandler {
    private final OrderService orderService;
    private final DeliveryService deliveryService;

    public OrderHandler(OrderService orderService, DeliveryService deliveryService) {
        this.orderService = orderService;
        this.deliveryService = deliveryService;
    }

    public Mono<ServerResponse> getOrderDetails(ServerRequest serverRequest) {
        String orderId = serverRequest.pathVariable("order-id");

        Mono<Order> foundOrder = orderService.findOrderBy(orderId);
        Mono<Delivery> foundDelivery = deliveryService.findDeliveryBy(orderId);

        return Mono
                .zip(foundOrder, foundDelivery)
                .map(OrderDetailsResponse::from)
                .flatMap(orderDetails -> ServerResponse.ok().bodyValue(orderDetails));
    }
}
