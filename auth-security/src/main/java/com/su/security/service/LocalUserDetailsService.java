package com.su.security.service;

import com.su.security.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.security.service
 * @ClassName: UserDetailsService
 * @Author: ssp
 * @Description:
 * @Date: 21/1/9 22:29
 * @Version: 1.0
 */
@Service
public class LocalUserDetailsService implements UserDetailsService {
    UserRepository repository;

    public LocalUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findById(s).orElse(null);
    }

}
