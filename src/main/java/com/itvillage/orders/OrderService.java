package com.itvillage.orders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
public class OrderService {
    @Value("${service.order.url}")
    private String orderServiceUrl;

    public Mono<Order> findOrderBy(String orderId) {
        URI gerOrderUri = UriComponentsBuilder.fromUriString(orderServiceUrl)
                .path("/orders/{order-id}")
                .build()
                .expand(orderId)
                .encode()
                .toUri(); // http://localhost:7070/v1/orders/{order-id}

        // TODO: Delivery 서비스 데이터를 가져온 후, OrderDetails로 response를 바꿔야 한다.

        return WebClient.create()
                .get()
                .uri(gerOrderUri)
                .retrieve()
                .bodyToMono(Order.class);
    }
}
