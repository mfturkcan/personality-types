package com.codecrew.personalitytest.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @GetMapping
    public ModelAndView getArticles() {

        return new ModelAndView("article/index");
    }

    @GetMapping("/{articleId}")
    public ModelAndView getArticle(@PathVariable int articleId) {

        return new ModelAndView("article/index");
    }
}
