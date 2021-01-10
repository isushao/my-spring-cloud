package com.su.cloud.controller;

import com.su.cloud.entity.Product;
import com.su.cloud.feign.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.cloud.controller
 * @ClassName: OrderController
 * @Author: ssp
 * @Description:
 * @Date: 21/1/6 21:53
 * @Version: 1.0
 */
@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Qualifier("com.su.cloud.feign.ProductFeignClient")
    @Autowired
    private ProductFeignClient productFeignClient;

    @GetMapping("/buy/{id}")
    public String order(@PathVariable Integer id){
        Product product = productFeignClient.findById(id);
        assert product != null;
        return String.format("成功下单了：%s",product.getName());
    }
}
