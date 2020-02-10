package org.pp.springboot_mybatis.controller;

import org.pp.context.ApplicationContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/admin/hello")
    public String admin() {
        return "hello";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "hello";
    }

    @GetMapping("/db/hello")
    public String dba() {
        return "hello";
    }

    @GetMapping("/hello")
    public String hello() {
//        ApplicationContextHolder.getApplicationContext().getBean(UserController.class);// nullpointer
        return "hello";
    }
}
