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
                        .path("/graphql")
                        .uri("http://localhost:8081/graphql"))
                .build();
    }
}
