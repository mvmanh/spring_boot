package com.mvm.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String index() {
        return "SpringBoot WebApp có tích hợp Code Build";
    }
}
