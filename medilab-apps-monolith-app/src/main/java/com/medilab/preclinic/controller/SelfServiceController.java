package com.medilab.preclinic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/self-service")
public class SelfServiceController {

    @GetMapping("/signup")
    public String register(){
        return  "Self Service Sign up here";
    }

    @GetMapping("/reset-pwd")
    public String resetPwd(){
        return  "Self Service RESET pwd here";
    }
}


