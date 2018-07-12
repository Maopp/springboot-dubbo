package com.boot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Reference(version = "mpp")
    private TestService testService;

    @RequestMapping("/hello")
    public String sayHello() {
        return testService.sayHello("hello spring boot dubbo");
    }

    @GetMapping("/user")
    public User getUser() {
        return testService.findUser();
    }
}
