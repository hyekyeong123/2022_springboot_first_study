package com.example.studyone.repository;


import com.example.studyone.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// @Repository - 따로 쿼리문을 작성하지 않아도 기본적인 CRUD 기능 제공

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // <어떠한 타입의 오브젝트, 기본키 타입>

    // JPA가 자동으로 select * from user where account = ? 이라고 인식함(메서드 이름 고정정
    Optional<User> findByAccount(String account);

    Optional<User> findByEmail(String email);

    // 여러가지로 함께 조회할때
    Optional<User> findByAccountAndEmail(String account, String email);
}
