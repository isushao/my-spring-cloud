package com.su.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.gateway.global.filter
 * @ClassName: AuthorizationFilter
 * @Author: ssp
 * @Description:
 * @Date: 21/1/9 13:21
 * @Version: 1.0
 */
@Component
@Slf4j
public class AuthorizationFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
