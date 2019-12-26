package com.lfq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 𝓛.𝓕.𝓠
 */
@Controller
@Slf4j
public class TestController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/hello")
    public String hello(){
        log.info("hello world!");
        return "Nice to meet you \uD835\uDCD2.\uD835\uDCE8!";
    }

}
