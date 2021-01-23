package com.su.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.time.Duration;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * 资源服务器配置
 */
@EnableWebFluxSecurity
public class ResourceServerConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}") String jwkSetUri;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange((exchanges) ->
                        exchanges
                                .pathMatchers(HttpMethod.GET,"/actuator/**").permitAll()
                                .pathMatchers(HttpMethod.GET, "/order-server/api/v1/**").hasAuthority("SCOPE_order:read")
                                .anyExchange().authenticated()
                )
                .oauth2ResourceServer((oauth2ResourceServer) ->
                        oauth2ResourceServer
                                .jwt(withDefaults())
                );

        return http.build();
    }

    @Bean
    ReactiveJwtDecoder jwtDecoder() {

        NimbusReactiveJwtDecoder jwtDecoder = NimbusReactiveJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
        //自定义时间戳验证
        OAuth2TokenValidator<Jwt> withClockSkew = new DelegatingOAuth2TokenValidator<>(
                new JwtTimestampValidator(Duration.ofSeconds(60))
//                ,new IssuerValidator(jwkSetUri)
        );

        jwtDecoder.setJwtValidator(withClockSkew);
        return jwtDecoder;
    }

    @Bean
    ServerCodecConfigurer serverCodecConfigurer(){
        return new DefaultServerCodecConfigurer();
    }
}
