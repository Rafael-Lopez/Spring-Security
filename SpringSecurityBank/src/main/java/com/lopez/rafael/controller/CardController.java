package com.lopez.rafael.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @GetMapping("/card")
    public String getCard(String input) {
        return "Card details";
    }
}
