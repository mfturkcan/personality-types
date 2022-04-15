package com.codecrew.personalitytest.restapi.controller;

import com.codecrew.personalitytest.restapi.dao.QuestionRepository;
import com.codecrew.personalitytest.restapi.dao.ResultRepository;
import com.codecrew.personalitytest.restapi.model.Answer;
import com.codecrew.personalitytest.restapi.model.PersonalityTrait;
import com.codecrew.personalitytest.restapi.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/result")
public class ResultController {
    private final ResultRepository resultRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public ResultController(ResultRepository resultRepository, QuestionRepository questionRepository) {
        this.resultRepository = resultRepository;
        this.questionRepository = questionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Result>> getResults() {
        return ResponseEntity.ok(resultRepository.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Result>> getResultsByPersonName(@PathVariable String name) {
        return ResponseEntity.ok(resultRepository.findByPersonName(name).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<Result> addResults(@RequestBody Answer answer){
        var result = new Result();
        result.setPersonName(answer.getPersonName());
        result.setGender(answer.getGender());

        List<PersonalityTrait> traits = new ArrayList<>();
        answer.getAnswers().stream().forEach(a -> {
            short i = 0;
            var question = questionRepository.findByQuestionNumber(i).orElseThrow();
            var traitType = a ? question.getCaseTrue(): question.getCaseFalse();

            traits.add(
                    PersonalityTrait.builder()
                            .questionNumber(i++)
                            .traitType(traitType)
                            .point(question.getPoint())
                            .traitGroup(question.getTraitGroup())
                            .build()
            );
        });

        result.setTraits(traits);

        resultRepository.save(result);

        return ResponseEntity.ok(result);
    }

    // TODO: ADD PATCH
    // TODO: ADD DELETE
}
