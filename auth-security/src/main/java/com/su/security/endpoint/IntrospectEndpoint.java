package com.su.security.endpoint;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Legacy Authorization Server (spring-security-oauth2) does not support any
 * Token Introspection endpoint.
 * <p>
 * This class adds ad-hoc support in order to better support the other samples in the repo.
 */
@FrameworkEndpoint
class IntrospectEndpoint {
    TokenStore tokenStore;

    IntrospectEndpoint(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @GetMapping("/introspect")
    @ResponseBody
    public Object introspect(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", Strings.EMPTY);
        OAuth2AccessToken accessToken = this.tokenStore.readAccessToken(token);
        Map<String, Object> attributes = new HashMap<>();
        if (accessToken == null || accessToken.isExpired()) {
            attributes.put("active", false);
            return attributes;
        }

        OAuth2Authentication authentication = this.tokenStore.readAuthentication(token);

        attributes.put("active", true);
        attributes.put("exp", accessToken.getExpiration().getTime());
        attributes.put("scope", String.join(" ", accessToken.getScope()));
        attributes.put("sub", authentication.getName());

        return attributes;
    }
}
