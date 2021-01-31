//package com.su.gateway.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
//import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
//
//import java.util.function.Consumer;
//
//@EnableWebSecurity
//public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private OAuth2AuthorizedClientService authorizedClientService;
//    @Autowired
//    private ClientRegistrationRepository clientRegistrationRepository;
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorize -> authorize
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .authorizationEndpoint(authorization -> authorization
//                                .authorizationRequestResolver(
//                                        authorizationRequestResolver(this.clientRegistrationRepository)
//                                )
//                        )
//                );
//    }
//
//    private OAuth2AuthorizationRequestResolver authorizationRequestResolver(
//            ClientRegistrationRepository clientRegistrationRepository) {
//
//        DefaultOAuth2AuthorizationRequestResolver authorizationRequestResolver =
//                new DefaultOAuth2AuthorizationRequestResolver(
//                        clientRegistrationRepository, "/introspect");
//        authorizationRequestResolver.setAuthorizationRequestCustomizer(
//                authorizationRequestCustomizer());
//
//        return authorizationRequestResolver;
//    }
//
//    private Consumer<OAuth2AuthorizationRequest.Builder> authorizationRequestCustomizer() {
//        OAuth2AuthorizedClient authorizedClient =
//                this.authorizedClientService.loadAuthorizedClient("custom-client", "user");
//        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
//        return customizer -> customizer
//                .additionalParameters(params -> params.put("token", accessToken.getTokenValue()));
//    }
//
//}