package com.example.studyone.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 모든 생성자 생성
public class LoginParam {
    private String id;
    private String password;
    private String email;
}
