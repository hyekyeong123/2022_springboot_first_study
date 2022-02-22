package com.example.studyone.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account; // 변수의 이름과 컬럼명이 동일하다면  @Column(name="") 사용X
    private String email;
    private String phoneNumber; // 자바에서는 DB와 다르게 카멜케이스 사용
    private LocalDateTime created_at;
    private String created_by;

    private LocalDateTime updated_at;
    private String updated_by;

    // 1 : N 주문관계(본인을 기준으로)
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "user") // user는 OrderDetail의 변수 이름과 동일해야함
    private List<OrderDetail> orderDetailList;

}
