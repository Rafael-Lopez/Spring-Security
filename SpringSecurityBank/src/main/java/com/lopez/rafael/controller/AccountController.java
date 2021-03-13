package com.lopez.rafael.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/account")
    public String getAccount(String input) {
        return "Account details";
    }
}
