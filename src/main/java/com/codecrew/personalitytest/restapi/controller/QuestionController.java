package com.codecrew.personalitytest.restapi.controller;

import com.codecrew.personalitytest.restapi.dao.QuestionRepository;
import com.codecrew.personalitytest.restapi.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getQuestions(){
        var questions = questionRepository.findAll();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{questionNumber}")
    public ResponseEntity<Question> getQuestion(@PathVariable short questionNumber){
        var question = questionRepository.findByQuestionNumber(questionNumber).orElseThrow();
        return ResponseEntity.ok(question);
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        questionRepository.save(question);
        return ResponseEntity.ok(question);
    }

    @PatchMapping("/{questionNumber}")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question,@PathVariable short questionNumber){
        var old_question = questionRepository.findByQuestionNumber(questionNumber).orElseThrow();
        old_question.setQuestion(question.getQuestion());
        old_question.setCaseFalse(question.getCaseFalse());
        old_question.setCaseTrue(question.getCaseTrue());
        old_question.setPoint(question.getPoint());
        old_question.setTraitGroup(question.getTraitGroup());
        old_question.setQuestionNumber(question.getQuestionNumber());

        questionRepository.save(old_question);
        return ResponseEntity.ok(question);
    }

    @DeleteMapping("/{questionNumber}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable short questionNumber){
        var question = questionRepository.findByQuestionNumber(questionNumber).orElseThrow();
        questionRepository.delete(question);
        return ResponseEntity.ok(question);
    }
}
