package com.web.proj.WebService.ui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home") // http://localhost:8080/home
public class HomeController {


    @GetMapping
    public String getUser() {
        return "This is Home Method";
    }

}
