package com.rebianne.shopapi.repository;

import com.rebianne.shopapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //회원 아이디 찾기
    public User findByEmail(String email);

    //회원 정보검색: id
    public User findById(long id);

    //oauth 관련
    public User findById(String email);

}
