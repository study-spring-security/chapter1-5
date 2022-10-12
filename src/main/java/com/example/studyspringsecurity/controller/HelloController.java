package com.example.studyspringsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication a){
        System.out.println("a = " + a);
        return "hello " +a.getName() +"!";
    }

    @GetMapping("/home")
    public String home(){
        return "home.html";
    }
}
