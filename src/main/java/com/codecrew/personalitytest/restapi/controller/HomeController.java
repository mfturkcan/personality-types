package com.codecrew.personalitytest.restapi.controller;

import com.codecrew.personalitytest.restapi.model.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    @Autowired
    private final Result result = new Result();

    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/test")
    public String getTest() {
        return "test";
    }

    @GetMapping("/swagger")
    public RedirectView getSwagger() {
        return new RedirectView("/swagger-ui/index.html");
    }
}
