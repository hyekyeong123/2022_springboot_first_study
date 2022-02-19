package com.example.studyone.controller;

import com.example.studyone.model.LoginParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // 클래스 매핑은 겹쳐도 문제가 없음
public class PostController {

    @PostMapping("/postMethod")
    public String PostMethod(@RequestBody LoginParam loginParam){ // body에서 가져올 예정
        return "OK";
    }
}
