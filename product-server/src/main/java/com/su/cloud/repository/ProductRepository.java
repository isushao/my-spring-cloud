package com.su.cloud.repository;

import com.su.cloud.entity.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.cloud.resposity
 * @ClassName: ProductResposity
 * @Author: ssp
 * @Description:
 * @Date: 21/1/6 21:41
 * @Version: 1.0
 */
public interface ProductRepository extends CrudRepository<Product,Integer> {
}
