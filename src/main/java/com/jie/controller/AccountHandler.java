package com.jie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountHandler {

    @GetMapping("/redirect/{target}")
    public String redirect(@PathVariable("target") String target){
        return target;
    }

}
