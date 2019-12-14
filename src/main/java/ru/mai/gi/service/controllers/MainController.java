package ru.mai.gi.service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String main(){
        return "gi-service";
    }

    @GetMapping("/hello")
    public String error(){ return "Hello world"; }
}
