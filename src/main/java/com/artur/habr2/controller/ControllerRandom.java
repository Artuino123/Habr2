package com.artur.habr2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerRandom {
    @GetMapping("/api/v1/random")
    public String string(){
        return "Hello World";
    }
}