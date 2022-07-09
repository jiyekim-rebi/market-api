package com.rebianne.shopapi.controller;

import com.rebianne.shopapi.entity.User;
import com.rebianne.shopapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    //회원가입
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User saveUser = userService.saveUser(user);

        //회원가입 처리 한 후에 후처리를 위한 URL Setting
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                       .path("{id}")
                       .buildAndExpand(saveUser.getId())
                       .toUri();

        return ResponseEntity.created(location).build();

    }

    //회원 정보 불러오기: id값이 있을 때
    @GetMapping("/search/{id}")
    public User retrieveUser(@PathVariable long id){
        User user = userService.findUserId(id);
        if(user == null)
            //User관련 별도의 Exception 필요하면 별도 class로 뺄것. [2022.07.09]
            throw new RuntimeException(String.format("ID[%s] not found", id));

        return user;
    }

    //로그인 처리 관련: user email, user password 값 받아서 맞는지 검사하고 맞으면 회원정보 return


}
