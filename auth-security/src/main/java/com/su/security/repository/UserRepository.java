package com.su.security.repository;

import com.su.security.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.security.repository
 * @ClassName: UserRepository
 * @Author: ssp
 * @Description:
 * @Date: 21/1/9 22:19
 * @Version: 1.0
 */
public interface UserRepository extends CrudRepository<User,String>, QueryByExampleExecutor<User> {
}
