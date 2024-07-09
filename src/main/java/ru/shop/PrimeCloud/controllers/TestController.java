package ru.shop.PrimeCloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/welcome")
    public String helloWorld() {
        return "welcome";
    }
}
