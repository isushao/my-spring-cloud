package com.su.security.service;

import com.su.security.model.User;
import com.su.security.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.security.service
 * @ClassName: LocalUserDetailsServiceTest
 * @Author: ssp
 * @Description:
 * @Date: 21/1/11 19:20
 * @Version: 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class LocalUserDetailsServiceTest {

    @Autowired
    UserRepository repository;

    @Test
    void loadUserByUsername() {
       User user = repository.findById("user").orElse(new User());
        System.out.printf(user.getUsername());
    }
}