package com.group.azura.maraissa.controleQualite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {
    @GetMapping("/")
    public String home(){
        return "redirect:/swagger-ui.html";
    }
}