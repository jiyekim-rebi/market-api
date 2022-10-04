package com.rebianne.shopapi.service;

import com.rebianne.shopapi.entity.User;
import com.rebianne.shopapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    //회원정보 검색(email)
    public User findUserEmail(String email){
        return userRepository.findByEmail(email);
    }

    //회원정보 검색(id)
    public User findUserId(long id) {
        return userRepository.findById(id);
    }

    //전체 회원 데이터 조회
    //id 오름차순으로 정렬 후 return
    public List<User> findAll(){
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    //회원정보 업데이트
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    //회원탈퇴처리
    public void deleteUser(User user){

    }

    //중복된 회원 체크
    private void validateDuplicateUser(User user){
        User findUser = userRepository.findByEmail(user.getEmail());
        if(findUser != null)
            throw new IllegalStateException("이미 가입된 회원입니다");
    }

}
