package com.su.security.endpoint;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.security.endpoint
 * @ClassName: UserInfoEndpoint
 * @Author: ssp
 * @Description:
 * @Date: 21/1/30 11:18
 * @Version: 1.0
 */
@FrameworkEndpoint
public class UserInfoEndpoint {

    @GetMapping("/oauth/userinfo")
    @ResponseBody
    public Principal user(Principal principal) {
        return principal;
    }
}
