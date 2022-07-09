package com.rebianne.shopapi.service;

import com.rebianne.shopapi.entity.User;
import com.rebianne.shopapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//Transaction ACID
//https://bamdule.tistory.com/51
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원등록
    public User saveUser(User user){
        validateDuplicateUser(user);
        return userRepository.save(user);
    }

    //중복된 회원 체크
    private void validateDuplicateUser(User user){
        User findUser = userRepository.findByEmail(user.getEmail());
        if(findUser != null)
            throw new IllegalStateException("이미 가입된 회원입니다");
    }

    //회원정보 검색(email)
    public User findUserEmail(String email){
        return userRepository.findByEmail(email);
    }

    //회원정보 검색(id)
    public User findUserId(long id) {
        return userRepository.findById(id);
    }

}
