package com.rebianne.shopapi.controller;

import com.rebianne.shopapi.entity.SuccessResponse;
import com.rebianne.shopapi.entity.User;
import com.rebianne.shopapi.exception.UserException;
import com.rebianne.shopapi.exception.UserNotFoundException;
import com.rebianne.shopapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //특정 회원 검색
    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = userService.findUserId(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user;
    }

    //회원가입
    @PostMapping()
    public ResponseEntity<SuccessResponse> createUser(User user){
        User createUser = userService.saveUser(user);

        if (createUser == null){
            throw new UserException("Cannot Create User");
        }

        SuccessResponse success = new SuccessResponse();
        success.setId(createUser.getId());

        return new ResponseEntity<>(success, HttpStatus.CREATED);
    }
    
    //회원 정보수정
    @PatchMapping()
    public ResponseEntity updateUser(User user){
        User searchUser = userService.findUserId(user.getId());

        if (searchUser == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", user.getId()));
        }

        User updateUser = userService.updateUser(user);

        if (updateUser == null){
            throw new UserException("Cannot Update user");
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //회원 탈퇴
    @DeleteMapping()
    public ResponseEntity deleteUser(User user) {

        User searchUser = userService.findUserId(user.getId());

        if (searchUser == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", user.getId()));
        }



        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
