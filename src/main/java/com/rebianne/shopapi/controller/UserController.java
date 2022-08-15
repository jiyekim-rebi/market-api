package com.rebianne.shopapi.controller;

import com.rebianne.shopapi.entity.User;
import com.rebianne.shopapi.exception.UserNotFoundException;
import com.rebianne.shopapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 *
 * /users GET = 회원 전체목록 불러오기
 * /users/{id} GET = 특정 회원 정보 불러오기
 * /users POST = 회원가입
 * /users PATCH = 회원정보수정
 * /users DELETE = 회원삭제(하지만 실제로는 update처리 = row 제거 안해요.)
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원 전체목록 불러오기
    @GetMapping()
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = userService.findUserId(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user;
    }

}
