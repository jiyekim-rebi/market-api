package com.rebianne.shopapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

//리소스서버 config class
@Configuration
public class Oauth2ResourceConfig extends ResourceServerConfigurerAdapter {

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

    //토큰 유효성 체크
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();

        remoteTokenServices.setClientId("clientId");
        remoteTokenServices.setClientSecret("secretKey");
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9090/oauth/check_token");
        resources.tokenServices(remoteTokenServices);
    }

}
