package com.example.studyone.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"user", "item"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    private LocalDateTime orderAt; // 언제 주문하였는가

    // ************** 외래키 등록하기 ****************
    // N(자신) : 1(상대) 관계
    @ManyToOne
    private User user; // 객체로 저장

    @ManyToOne
    private Item item;
//    private Integer itemId;

    // java.lang.StackOverflowError에러
    // OrderDetail안의 유저와 아이템이 각각 toString을 오버라이딩해서
    // 반드시 연관관계 설정에 대한 변수에 대해서는 exclude 시켜주기
}
