package com.itvillage.orders;

import com.itvillage.delivery.Delivery;
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
                .toUri(); // http://localhost:7070/orders/{order-id}

        return WebClient.create()
                .get()
                .uri(gerOrderUri)
                .retrieve()
                .bodyToMono(Order.class);
    }
}
