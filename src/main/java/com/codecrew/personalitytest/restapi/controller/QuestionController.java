package com.codecrew.personalitytest.restapi.controller;

import com.codecrew.personalitytest.restapi.dao.QuestionRepository;
import com.codecrew.personalitytest.restapi.model.Answer;
import com.codecrew.personalitytest.restapi.model.Question;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/question")
public class QuestionController {
    private final QuestionRepository questionRepository;

    @Autowired
    private final Answer answer;
    static int PAGE_SIZE = 8;

    @Autowired
    public QuestionController(QuestionRepository questionRepository, Answer answer) {
        this.questionRepository = questionRepository;
        this.answer = answer;
    }

    @GetMapping
    public String getQuestions(Model model){
        List<Question> questions = questionRepository.findAll(Sort.by("questionNumber"));
        model.addAttribute("questions", questions);

        return "question/index";
    }


    @GetMapping("/{pageNo}")
    public String getQuestionsByPage(Model model,
                                     @PathVariable(required = false) Optional<Integer> pageNo,
                                     Answer answer){
        if(!pageNo.isPresent()) pageNo = Optional.of(0);
        Pageable pageable = PageRequest.of(pageNo.get(), PAGE_SIZE, Sort.by("questionNumber"));
        List<Question> questions = questionRepository.findAll(pageable).toList();

        model.addAttribute("answer", answer);
        model.addAttribute("questions", questions);

        return "question/index";
    }


    @PostMapping("/{pageNo}")
    public RedirectView postQuestion(@RequestBody List<Question> questions,
                                     @PathVariable int pageNo,
                                     Model model){
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);

        if(questionRepository.count() / PAGE_SIZE  <= pageNo){
            // last page
            // return answer to result and save
            // return result page with result object
            return redirectView;
        }
        // else, add values to answer list
        model.addAttribute("pageNo",pageNo+1);
        redirectView.setUrl("question/index/{pageNo}");

        return redirectView;
    }
}
