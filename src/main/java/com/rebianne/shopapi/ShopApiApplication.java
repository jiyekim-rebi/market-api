package com.rebianne.shopapi;

import com.rebianne.shopapi.constant.Level;
import com.rebianne.shopapi.entity.User;
import com.rebianne.shopapi.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApiApplication.class, args);

        //sample user
        User user = new User();
        user.setEmail("rebianne03@naver.com");
        user.setPassword("{noop}test");
        user.setName("rebi");
        user.setLevel(Level.ADMIN);

    }

}
