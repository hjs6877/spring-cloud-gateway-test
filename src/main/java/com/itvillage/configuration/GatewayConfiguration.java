package com.itvillage.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class GatewayConfiguration {
    @Value("${service.order.url}")
    private String orderServiceUrl;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/orders")
                        .and()
                        .method(HttpMethod.POST)
                        .uri(orderServiceUrl))
                .route(r -> r
                        .path("/orders")
                        .and()
                        .method(HttpMethod.GET)
                        .uri(orderServiceUrl))
                .route(r -> r            // example-2-simple-1: 주문 ID에 해당하는 데이터를 조회하기 위한 단순 라우팅
                        .method(HttpMethod.GET)
                        .and()
                        .path("/orders/{order-id}")
                        .filters(f -> f
                                .rewritePath("/orders/(?<orderId>\\d+)",
                                        "/orders/${orderId}"))
                        .uri(orderServiceUrl))
                .route(r -> r    // example-2-simple-2: 회원 ID에 해당하는 주문 목록를 조회하기 위한 단순 라우팅
                        .method(HttpMethod.GET)
                        .and()
                        .path("/members/{member-Id}/orders")
                        .filters(f -> f
                                .rewritePath("/members/(?<memberId>\\d+)/orders",
                                        "/members/${memberId}/orders"))
                        .uri(orderServiceUrl))
                .build();
    }
}
