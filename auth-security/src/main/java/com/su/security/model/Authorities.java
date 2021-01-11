package com.su.security.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.security.model
 * @ClassName: Authorities
 * @Author: ssp
 * @Description:
 * @Date: 21/1/11 18:28
 * @Version: 1.0
 */
@Data
@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {
    @Id
    private String username;
    private String authority;
}
