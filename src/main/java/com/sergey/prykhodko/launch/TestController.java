package com.sergey.prykhodko.launch;

import com.sergey.prykhodko.proxygeneration.PageGetter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return new PageGetter().getReorgionisedPage("https://ua.sportsdirect.com/");

    }
}
