package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/h")
public class HomeController {
    @GetMapping("/inicio")
    public String home() {
        return "Hola Mundo";
    }
}
