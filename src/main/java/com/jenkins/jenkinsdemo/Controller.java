package com.jenkins.jenkinsdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello(){
        return "Hello world!";
    }

    @GetMapping("/goodbye")
    public String goodbye(){
        return "Goodbye!";
    }

    @GetMapping("/works")
    public String works(){
        return "Finally the webhook works!";
    }

    public void bin(){}
}
