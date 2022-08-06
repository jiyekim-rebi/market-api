package com.rebianne.shopapi.configuration;

import com.rebianne.shopapi.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final UserDetailService userDetailService;
    private final AuthenticationManager authenticationManager;
    private final DataSource dataSource;

    //DB에 토큰 데이터 저장
    /*
    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }
    */

    //권한 동의 DB 저장
    @Bean
    public ApprovalStore approvalStore(){
        return new JdbcApprovalStore(dataSource);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        // RSA 암호화 : 비 대칭키 암호화 : 공개키로 암호화 하면 개인키로 복호화
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("rebianneKey.jks"), "rebianne03@naver.com".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwtKey"));

        return converter;
    }

    //security, clients, endpoints
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //super.configure(security);
       security.tokenKeyAccess("permitAll()") //전부 허용
               //.checkTokenAccess("isAuthenticated()") //인증된 사용자만 토큰 체크 가능 (/oauth/check_token)
               .allowFormAuthenticationForClients();
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*
        //client 설정
        clients.inMemory()
                .withClient("clientId")
                .secret("{noop}secretKey")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token") //토큰 발행 타입
                .scopes("read", "write")
                .accessTokenValiditySeconds(60 * 60)
                .refreshTokenValiditySeconds(60 * 60)
                .redirectUris("http://localhost:9090/callback")
                .autoApprove(true);
         */
        //인증 데이터 관련 DB로 연결
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userDetailsService(userDetailService)            //refrash token 발행시 유저 정보 검사 하는데 사용하는 서비스 설정
                 .authenticationManager(authenticationManager)
                 //.tokenStore(tokenStore())
                 .accessTokenConverter(jwtAccessTokenConverter())
                 .approvalStore(approvalStore());   //권한 동의 설정
    }

}
