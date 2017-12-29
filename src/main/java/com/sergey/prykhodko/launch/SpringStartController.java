package com.sergey.prykhodko.launch;

import com.sergey.prykhodko.proxygeneration.PageGetter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@EnableAutoConfiguration
public class SpringStartController {


    public static void main(String[] args) {
        SpringApplication.run(SpringStartController.class, args);
    }

    @Bean
    @RequestMapping("/test")
    public String test(){
        return new PageGetter().getReorgionisedPage("https://ua.sportsdirect.com/");

    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                if (beanName.contains("Test")) {
                    System.out.println(beanName);
                }
            }

        };
    }

}

