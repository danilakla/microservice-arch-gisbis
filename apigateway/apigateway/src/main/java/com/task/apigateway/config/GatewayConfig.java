package com.task.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {

    @Value("${test}")
    private String val;
    @Autowired
    private RouteLocatorBuilder builder;

    @Bean
    public RouteLocator customRouteLocator() {
        return builder.routes()
                .route("render-service-route", r -> r
                        .path("/render")
                        .uri("lb://render-service")
                )
                .build();
    }
}
