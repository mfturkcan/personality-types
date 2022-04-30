package com.codecrew.personalitytest.restapi.controller;

import com.codecrew.personalitytest.restapi.dao.QuestionRepository;
import com.codecrew.personalitytest.restapi.model.Answer;
import com.codecrew.personalitytest.restapi.model.Question;
import com.codecrew.personalitytest.restapi.model.Result;

import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
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
    private final Result result;
    static int PAGE_SIZE = 8;
    public static int QUESTION_COUNT;

    @Autowired
    public QuestionController(QuestionRepository questionRepository, Result result) {
        this.questionRepository = questionRepository;
        this.result = result;
        QUESTION_COUNT = (int) questionRepository.count();
    }

    @GetMapping
    public RedirectView getQuestions(Model model) {

        return new RedirectView("/question/0");
    }

    @GetMapping("/{pageNo}")
    public String getQuestionsByPage(Model model,
            @PathVariable(required = false) Optional<Integer> pageNo) {
        if (!pageNo.isPresent())
            pageNo = Optional.of(0);
        Pageable pageable = PageRequest.of(pageNo.get(),
                PAGE_SIZE,
                Sort.by("questionNumber"));
        List<Question> questions = questionRepository.findAll(pageable).toList();
        List<Answer> answers = new ArrayList<Answer>();

        questions.forEach(question -> {
            var answer = new Answer();
            answer.setQuestion(question);
            answers.add(answer);
        });

        model.addAttribute("answers", answers);

        return "question/index";
    }

    @RequestMapping(value = "/saveQuestions", consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.ALL_VALUE
    }, method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String postQuestion(@ModelAttribute("answers") ArrayList<Answer> answers,
            Model model) {

        System.out.println(answers.size());

        int currentQuestionCount = result.getAnswers().size() + answers.size();
        int pageNo = (currentQuestionCount) / PAGE_SIZE;

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(false);

        if (pageNo == QUESTION_COUNT / PAGE_SIZE) {
            // last page
            // return answer to result and save
            // return result page with result object
            System.out.println("Hello");
            redirectView.setUrl("/test");
            return "redirect:/test";
        }
        System.out.println("Hello 2");
        // else, add values to answer list
        model.addAttribute("pageNo", pageNo + 1);
        redirectView.setUrl("question/index/{pageNo}");

        return "question/index/" + pageNo;
    }
}
