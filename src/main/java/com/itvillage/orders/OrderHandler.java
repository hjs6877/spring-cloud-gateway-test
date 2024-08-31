package com.itvillage.orders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class OrderHandler {
    private final OrderService orderService;

    public OrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    public Mono<ServerResponse> getOrderDetails(ServerRequest serverRequest) {
        String orderId = serverRequest.pathVariable("order-id");
        log.info("# call OrderHandler > getOrderDetails()");
        return orderService.findOrderBy(orderId)
                .flatMap(foundOrder -> ServerResponse.ok().bodyValue(foundOrder));
    }
}
