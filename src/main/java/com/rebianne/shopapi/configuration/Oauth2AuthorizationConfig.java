package com.rebianne.shopapi.configuration;

import com.rebianne.shopapi.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@EnableAuthorizationServer
@RequiredArgsConstructor
@Configuration
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final UserDetailService userDetailService;

    //security, clients, endpoints
    /*
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }
    */

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //client 설정
        clients.inMemory()
                .withClient("clientId")
                .secret("{noop}secretKey")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token") //토큰 발행 타입
                .scopes("read", "write")
                .accessTokenValiditySeconds(60 * 60)
                .refreshTokenValiditySeconds(60 * 60)
                .redirectUris("htttp://localhost:9090/callback")
                .autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userDetailsService(userDetailService);
    }


}
