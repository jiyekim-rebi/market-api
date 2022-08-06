package com.rebianne.shopapi.configuration;

import com.google.gson.Gson;
import com.rebianne.shopapi.entity.JWTKey;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

//리소스서버 config class
//JWT 관련 내용 https://pronist.dev/143 참고 
@Configuration
public class Oauth2ResourceConfig extends ResourceServerConfigurerAdapter {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String publicKeyUri;

    //jwt 체크
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){

        try {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setVerifierKey(getPublicKeyUri(this.publicKeyUri));
            return converter;

        } catch (Exception e){
            return new JwtAccessTokenConverter();
        }
    }

    //API별 필요한 인증 정보 설정
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests()
                .antMatchers("/main")
                .access("#oauth2.hasAnyScope('read')")
                .anyRequest()
                .authenticated();
    }

    /*
    //토큰 유효성 체크
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();

        remoteTokenServices.setClientId("clientId");
        remoteTokenServices.setClientSecret("secretKey");
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9090/oauth/check_token");
        resources.tokenServices(remoteTokenServices);
    }
     */

    private String getPublicKeyUri(String uriKey){
        JsonNode response = Unirest.get(uriKey).asJson().getBody();

        return StringUtils.isEmpty(response.toString()) ? "" : new Gson().fromJson(response.toString(), JWTKey.class).getValue();
    }

}
