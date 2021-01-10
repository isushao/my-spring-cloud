package com.su.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.cloud
 * @ClassName: OrderApplication
 * @Author: ssp
 * @Description:
 * @Date: 21/1/6 21:51
 * @Version: 1.0
 */
@SpringBootApplication
@EnableFeignClients
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
