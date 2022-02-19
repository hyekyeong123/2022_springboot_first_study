package com.example.studyone.controller;

import com.example.studyone.model.LoginParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // 주소 매핑하기
public class GetController {

    // HTTP GET Method(주소 창에 파라미터가 노출됨)

    // 화면 보여주기
    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // localhost:8080/api/getMethod
    public String getRequest(){
        return "Hi getMethod";
    }

    // 문자열 파라미터
    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam String password){
        System.out.println("id: "+id);
        System.out.println("password: "+password);

        return id+"/"+password;
    }

    // 문자열 파라미터의 개수가 많을때 model 사용하기
    @GetMapping("/getMultParameter") // localhost:8080/api/getMultParameter?id=1234&password=abcd&eamil=qwert@naver.com
    public String getMultParameter(LoginParam loginParam){
        System.out.println(loginParam.getId());
        System.out.println(loginParam.getPassword());
        System.out.println(loginParam.getEmail());
        return "성공";
    }

    // 문자열 파라미터를 json 형태로 보여주기
    @GetMapping("/getJsonMultParameter")
    public LoginParam getJsonMultParameter(LoginParam loginParam){// localhost:8080/api/getJsonMultParameter?id=1234&password=abcd&eamil=qwert@naver.com
        System.out.println(loginParam.getId());
        System.out.println(loginParam.getPassword());
        System.out.println(loginParam.getEmail());
        return loginParam; // {"id":"1234","password":"abcd","email":null}
        // 기본적으로 jackson 라이브러리 내장
    }
}
