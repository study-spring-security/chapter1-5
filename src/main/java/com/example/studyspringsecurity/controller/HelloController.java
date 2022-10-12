package com.example.studyspringsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication a){
        System.out.println("a = " + a);
        return "hello " +a.getName() +"!";
    }
}
