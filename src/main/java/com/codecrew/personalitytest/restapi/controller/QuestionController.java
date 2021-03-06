package com.codecrew.personalitytest.restapi.controller;

import com.codecrew.personalitytest.restapi.dao.QuestionRepository;
import com.codecrew.personalitytest.restapi.exception.ResourceNotFoundException;
import com.codecrew.personalitytest.restapi.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@RestController(value = "api.question")
@RequestMapping("/api/v1/question")
public class QuestionController {
    private final QuestionRepository questionRepository;
    static int PAGE_SIZE = 8;
    public static int QUESTION_COUNT;

    @Autowired
    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getQuestions() {
        var questions = questionRepository.findAll();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/questionNumber")
    public ResponseEntity<List<Question>> getByQuestionNumber() {
        var questions = questionRepository.findAll(Sort.by("questionNumber"));

        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{pageNo}")
    public ResponseEntity<List<Question>> getQuestionsByPage(
            @PathVariable(required = false) Optional<Integer> pageNo) {
        if (!pageNo.isPresent())
            pageNo = Optional.of(0);
        Pageable pageable = PageRequest.of(pageNo.get(),
                PAGE_SIZE,
                Sort.by("questionNumber"));
        List<Question> questions = questionRepository.findAll(pageable).toList();

        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable int id) throws ResourceNotFoundException {
        var question = questionRepository.findById(
                id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found " + id));
        return ResponseEntity.ok(question);
    }

    @GetMapping("count")
    public ResponseEntity<Long> getQuestionsCount() {
        var count = questionRepository.count();

        return ResponseEntity.ok(count);
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        questionRepository.save(question);
        return ResponseEntity.ok(question);
    }

    @Transactional
    @PostMapping("/addMultiple")
    public ResponseEntity<List<Question>> addQuestions(@RequestBody List<Question> questions) {
        questionRepository.saveAll(questions);

        return ResponseEntity.ok(questions);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question, @PathVariable int id) {
        var old_question = questionRepository.findById(id).orElseThrow();
        old_question.setQuestion(question.getQuestion());
        old_question.setCaseFalse(question.getCaseFalse());
        old_question.setCaseTrue(question.getCaseTrue());
        old_question.setCaseTruePoint(question.getCaseTruePoint());
        old_question.setCaseFalsePoint(question.getCaseFalsePoint());
        old_question.setQuestionNumber(question.getQuestionNumber());
        old_question.setTraitGroup(question.getTraitGroup());

        questionRepository.save(old_question);
        return ResponseEntity.ok(question);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable int id)
            throws ResourceNotFoundException {
        var question = questionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Question not found " + id));
        questionRepository.delete(question);
        return ResponseEntity.ok(question);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        questionRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
