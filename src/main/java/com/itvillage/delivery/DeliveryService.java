package com.itvillage.delivery;

import com.itvillage.orders.OrderDetailsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
public class DeliveryService {
    @Value("${service.delivery.url}")
    private String deliveryServiceUrl;

    public Mono<Delivery> findDeliveryBy(String orderId) {

        URI gerDeliveryUri = UriComponentsBuilder.fromUriString(deliveryServiceUrl)
                .path("/orders/{order-id}/deliveries")
                .build()
                .expand(orderId)
                .encode()
                .toUri(); // http://localhost:6060/orders/{order-id}/deliveries

        return WebClient.create()
                .get()
                .uri(gerDeliveryUri)
                .retrieve()
                .bodyToMono(Delivery.class);
    }
}
