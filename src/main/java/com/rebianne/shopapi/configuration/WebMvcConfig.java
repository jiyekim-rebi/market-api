package com.rebianne.shopapi.configuration;

import com.rebianne.shopapi.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebMvcConfig {

    //Overriding
    @Bean
    public UserDetailService userDetailService(){
        return new UserDetailService();
    }

}
