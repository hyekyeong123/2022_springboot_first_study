package com.example.studyone.repository;

import com.example.studyone.StudyoneApplicationTests;
import com.example.studyone.model.entity.Item;
import com.example.studyone.model.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryTest extends StudyoneApplicationTests {

    @Autowired // 직접 객체 만들지 않고 스프링이 의존성 주입(DI - Dependency Injection)
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        // user.setId(); id는 auto_increment
        user.setAccount("한글 입력 안됨");
        user.setEmail("TestUser02@naver.com");
        user.setPhoneNumber("한글 입력 안됨");
        user.setCreated_at(LocalDateTime.now());
        user.setCreated_by("admiN3");

        User newUser = userRepository.save(user);
        System.out.println("[JHG] : "+newUser);
    }

    @Test
    @Transactional
    public void read(){

        // select * from user where id = ?(idx로 유저 찾기)
        // Optional<User> user = userRepository.findById(12L);
        Optional<User> user = userRepository.findByAccount("한글 입력 안됨");

        // user라는 값이 들어 있다면
        user.ifPresent(selectUser->{
            selectUser.getOrderDetailList().stream().forEach(detail->{

                // 어떤 아이템을 가져왔는지 확인인
                Item item = detail.getItem();
                System.out.println(item);

                // 12번인 사용자가 아래 주문을 함함
               // Item(id=1, name=노트북, price=100000, content=한글 입력 안됨, orderDetailList=[OrderDetail(id=1, orderAt=2022-02-22T14:08:11)])

            });
        });

    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(u->{
            u.setAccount("ppp");
            u.setUpdated_at(LocalDateTime.now());
            u.setUpdated_by("update method()");

            // id가 있다면 update, 없다면 insert
            userRepository.save(u);
            System.out.println("[JHG] user D : "+u);
            // [JHG] user D : User(id=2, account=ppp, email=TestUser01@naver.com, phoneNumber=010-1111-1111, created_at=2022-02-20T16:45:55, created_by=admin, updated_at=2022-02-20T21:55:52.998255500, updated_by=update method())
        });
    }

    @Test
    @Transactional // 실제 DB에 영향을 미치지 않음
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        // 반드시 2번이 존재해야함
        assertTrue(user.isPresent());

        user.ifPresent(u->{
            userRepository.delete(u);
        });

        Optional<User> deleteUser = userRepository.findById(3L);
        assertFalse(deleteUser.isPresent()); // 반드시 false여야함
    }

    // 일반적으로 assertTrue, assertFalse 메소드는 boolean 값을 리턴하는 메소드를 테스트하기에 적합
    // 이름 사용할려면 junit이 필요
}