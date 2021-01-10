package com.su.cloud.feign;

import com.su.cloud.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.cloud.feign
 * @ClassName: ProductFeignClient
 * @Author: ssp
 * @Description:
 * @Date: 21/1/7 22:38
 * @Version: 1.0
 */

@FeignClient(value = "product-server", fallback = ProductFeignClientCallBack.class)
public interface ProductFeignClient {

    @RequestMapping(value = "/api/v1/product/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable Integer id);
}
