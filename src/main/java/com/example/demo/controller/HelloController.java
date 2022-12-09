package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "<h1>Hello World</h1>";
    }

    @GetMapping("/")
    public String accueil() {
        return "<h1>Le serveur marche !</h1>";
    }
}
