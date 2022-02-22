package com.example.studyone.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data // lombok -> Setter, Getter, ToString, Constructor에 대한 정보를 자동적으로 생성해줍니다.
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private String content;

    // 1 : N
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}
//@Data : lombok -> Setter, Getter, ToString, Constructor에 대한 정보를 자동적으로 생성해줍니다.

// FetchType
// -> LAZY - 지연로딩 select user * from where id = ? // 연관관계 무시
// -> EAGER - 즉시로딩 // 연관관계 설정된 모든 테이블에 대해서 조인(1 : 1일때만 사용할 것)


