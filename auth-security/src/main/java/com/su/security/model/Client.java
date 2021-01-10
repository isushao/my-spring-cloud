package com.su.security.model;

import lombok.Data;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectName: my-spring-cloud
 * @Package: com.su.security.model
 * @ClassName: Client
 * @Author: ssp
 * @Description:
 * @Date: 21/1/9 22:19
 * @Version: 1.0
 */
//@Data
//@Entity
//@Table(name = "oauth_client_details")
//public class Client implements ClientDetails {
//    @Id
//    private Integer id;
//
//    private String clientId;
//    private String secret;
//    private String scope;
//    private String grantType;
//    private String authorities;
//    private String redirectUri;
//
//    @Override
//    public Set<String> getResourceIds() {
//        return new HashSet<>();
//    }
//
//    @Override
//    public boolean isSecretRequired() {
//        return true;
//    }
//
//    @Override
//    public String getClientSecret() {
//        return this.secret;
//    }
//
//    @Override
//    public boolean isScoped() {
//        return true;
//    }
//
//    @Override
//    public Set<String> getAuthorizedGrantTypes() {
//        return new HashSet<>();
//    }
//
//    @Override
//    public Set<String> getRegisteredRedirectUri() {
//        return new HashSet<>();
//    }
//
//    @Override
//    public Integer getAccessTokenValiditySeconds() {
//        return 2000;
//    }
//
//    @Override
//    public Integer getRefreshTokenValiditySeconds() {
//        return 2000;
//    }
//
//    @Override
//    public boolean isAutoApprove(String s) {
//        return true;
//    }
//
//    @Override
//    public Map<String, Object> getAdditionalInformation() {
//        return new HashMap<>();
//    }
//}
