package com.itvillage.configuration;

import com.itvillage.orders.OrderHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class GatewayConfiguration {
    @Value("${service.order.url}")
    private String orderServiceUrl;

    @Bean
    public RouteLocator routeOrderProxy(RouteLocatorBuilder builder) {
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
                .route(r -> r
                        .method(HttpMethod.GET)
                        .and()
                        .path("/members/{member-Id}/orders")
                        .filters(f -> f
                                .rewritePath("/members/(?<memberId>\\d+)/orders",
                                        "/members/${memberId}/orders"))
                        .uri(orderServiceUrl))
                .build();
    }

    // example-3-combine-orderDetails: 주문 정보, 배달 정보를 조합해서 클라이언트에게 전송하기 위한 OrderHandler로의 라우팅
    @Bean
    public RouterFunction<ServerResponse> routeOrderHandler(OrderHandler orderHandler) {
        return RouterFunctions.route(GET("/orders/{order-id}"), orderHandler::getOrderDetails);
    }

}
