package com.su.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.gateway.config
 * @ClassName: GatewayConfiguration
 * @Author: ssp
 * @Description: sentinel 限流配置
 * @Date: 21/1/17 22:01
 * @Version: 1.0
 */
@Configuration
public class GatewayConfiguration {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    @Bean
    @Order(2)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    /**
     * 限流过滤器
     * @return
     */
    @Bean
    @Order(2)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    /**
     * 初始化限流参数
     *      用于指定资源的限流规则
     *          1、资源名称（路由id）
     *          2、配置统计时间 单位时间内最大请求数量
     *          3、配置限流阈值
     * 可sentinel控制台动态管理 不用非得在这里配置
     */
    @PostConstruct
    public void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();

        rules.add(new GatewayFlowRule("auth-security-server")
                // 每秒只接受1次请求
                .setCount(1)
                .setIntervalSec(1)
        );
        rules.add(new GatewayFlowRule("order-server")
                .setCount(1)
                .setIntervalSec(1)
        );
        rules.add(new GatewayFlowRule("product-server")
                .setCount(1)
                .setIntervalSec(1)
        );

        // 添加自定义的api限流分组
        rules.add(new GatewayFlowRule("product_api")
                .setCount(1)
                .setIntervalSec(1)
        );
        GatewayRuleManager.loadRules(rules);
    }

    /**
     * 自定义api限流分组
     */
    @PostConstruct
    public void initCustomizedApis(){
        Set<ApiDefinition> definitions = new HashSet<>();
        ApiDefinition api1 = new ApiDefinition("order_api")
                .setPredicateItems(new HashSet<ApiPredicateItem>(){{
                    // 匹配/order-server/api/v1/order/ 开头的接口地址
                    add(new ApiPathPredicateItem().setPattern("/order-server/api/v1/order/**")
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                }});

        ApiDefinition api2 = new ApiDefinition("product_api")
                .setPredicateItems(new HashSet<ApiPredicateItem>(){{
                    // 完全匹配 /product-server/api/v1/product
                    add(new ApiPathPredicateItem().setPattern("/product-server/api/v1/product")
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                }});
        definitions.add(api1);
        definitions.add(api2);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }


    /**
     * 自定义异常
     */
    @PostConstruct
    public void initBlockHandler(){
        BlockRequestHandler blockRequestHandler = (serverWebExchange, throwable) -> {
            Map<String,Object> map = new HashMap();
            map.put("message","限流了");
            map.put("code",444);

            return ServerResponse.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(map));
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }

}
