package com.rebianne.shopapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @RequestMapping("/main")
    public String main(){
        return "Success";
    }
}
