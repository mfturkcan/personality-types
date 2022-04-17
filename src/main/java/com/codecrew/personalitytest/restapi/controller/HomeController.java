package com.codecrew.personalitytest.restapi.controller;

import com.codecrew.personalitytest.restapi.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    @Autowired
    private final Answer answer = new Answer();

    @GetMapping("/")
    public String getHome(){
        return "index";
    }

    @GetMapping("/swagger")
    public RedirectView getSwagger(){
        return new RedirectView("/swagger-ui/index.html");
    }
}
