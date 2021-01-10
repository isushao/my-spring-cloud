package com.su.cloud.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    private int id;
    private String name;
    private BigDecimal price;
    private String detail;
}
