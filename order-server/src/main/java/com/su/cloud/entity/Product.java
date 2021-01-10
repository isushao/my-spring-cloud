package com.su.cloud.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.cloud.entity
 * @ClassName: Product
 * @Author: ssp
 * @Description:
 * @Date: 21/1/6 21:38
 * @Version: 1.0
 */
@Data
@Builder
public class Product implements Serializable {
    private int id;
    private String name;
    private BigDecimal price;
    private String detail;
}
