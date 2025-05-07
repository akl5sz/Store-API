package com.codewithmosh.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(Model model){ //model is a container for data, we use it to pass data from a controller to a view
        model.addAttribute("name", "Angie");
        return "index"; //removed html because we are now working with a thymeleaf template
    }   
}
