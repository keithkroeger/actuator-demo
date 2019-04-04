package com.example.demo.web;

import com.example.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class HelloWorld {

    @Autowired
    HelloService helloService;

    @GetMapping("/test")
    public String helloWorld(HttpServletResponse response){
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        helloService.handleMessage("test");
        return "{\"status\":\"Hello, is it me you're looking for?\"}";
    }

    @GetMapping("/test2")
    public String helloWorld2(){
        helloService.handleMessage("test");
        return "Hello, is it me you're looking for?";
    }

}
