package com.lopez.rafael.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @GetMapping("/loan")
    public String getLoan(String input) {
        return "Loan details";
    }
}
