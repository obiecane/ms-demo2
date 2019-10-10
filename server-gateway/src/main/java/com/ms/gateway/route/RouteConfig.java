package com.ms.gateway.route;

import com.ms.gateway.filter.JWTAuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/8/29 10:37
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Configuration
public class RouteConfig {


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        // TODO 可以在网关做分发, 鉴权, 限流, 过滤
        return builder.routes()
                .route(p -> p
                        .path("/members/**")
                        .filters(f -> f.filter(new JWTAuthFilter(), -100))
//                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("lb://MEMBER-SERVICE"))
                .route(p -> p
                        .path("/orders/**")
                        .uri("lb://ORDER-SERVICE"))
                .build();
    }
}
