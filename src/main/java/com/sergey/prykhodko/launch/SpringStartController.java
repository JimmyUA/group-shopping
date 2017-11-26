package com.sergey.prykhodko.launch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;

@Controller
@EnableAutoConfiguration
public class SpringStartController {

    public static void main(String[] args) {
        SpringApplication.run(SpringStartController.class, args);
    }
}
