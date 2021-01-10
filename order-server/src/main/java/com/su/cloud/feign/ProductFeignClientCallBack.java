package com.su.cloud.feign;

import com.su.cloud.entity.Product;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.cloud.feign
 * @ClassName: ProductFeignClientCallBack
 * @Author: ssp
 * @Description:
 * @Date: 21/1/7 22:57
 * @Version: 1.0
 */
@Component
public class ProductFeignClientCallBack implements ProductFeignClient{

    @Override
    public Product findById(Integer id) {
        return Product
                .builder()
                .name("feign熔断")
                .build();
    }
}
