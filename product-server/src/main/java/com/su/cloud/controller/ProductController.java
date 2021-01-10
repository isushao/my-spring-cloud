package com.su.cloud.controller;

import com.su.cloud.entity.Product;
import com.su.cloud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.cloud.controller
 * @ClassName: ProductController
 * @Author: ssp
 * @Description:
 * @Date: 21/1/6 21:42
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getById(@PathVariable Integer id) throws InterruptedException {
        return repository.findById(id).orElse(new Product());
    }
}
