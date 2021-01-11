package com.su.security.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.security.model
 * @ClassName: User
 * @Author: ssp
 * @Description:
 * @Date: 21/1/9 22:18
 * @Version: 1.0
 */
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    private String username;
    private String password;
    private int enabled;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "username")
    List<Authorities> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> result = new ArrayList<>();
        List<String> list = authorities.stream().map(Authorities::getAuthority).collect(Collectors.toList());
        for (String item : list) {
            result.add(new SimpleGrantedAuthority(item));
        }
        return result;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return 0 != enabled;
    }
}
