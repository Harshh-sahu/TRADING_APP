package com.zosh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home(){

        return "WELCOME TO TREADING PLATFORM";
    }

    @GetMapping("/api")
    public String secure(){
        return "welcome to treading platform secure";
    }
}
