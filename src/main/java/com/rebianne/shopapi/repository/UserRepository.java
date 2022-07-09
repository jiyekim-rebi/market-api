package com.rebianne.shopapi.repository;

import com.rebianne.shopapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //회원 아이디 찾기
    public User findByEmail(String email);

    //회원 정보검색: id
    public User findById(long id);

}
