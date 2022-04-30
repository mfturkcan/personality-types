package com.codecrew.personalitytest.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personalityTraits")
public class TraitController {

    @GetMapping
    public String index() {
        return "trait/index";
    }

}
