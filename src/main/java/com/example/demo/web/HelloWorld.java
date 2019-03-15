package com.example.demo.web;

import com.example.demo.service.HelloService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloWorld {

    @Autowired
    HelloService helloService;

    @GetMapping("/")
    public String helloWorld(){
        helloService.handleMessage("test");
        return "Hello, is it me you're looking for?";
    }

}
