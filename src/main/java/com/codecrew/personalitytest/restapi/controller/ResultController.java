package com.codecrew.personalitytest.restapi.controller;

import com.codecrew.personalitytest.restapi.dao.QuestionRepository;
import com.codecrew.personalitytest.restapi.dao.ResultRepository;
import com.codecrew.personalitytest.restapi.enums.PersonalityTraitGroup;
import com.codecrew.personalitytest.restapi.enums.PersonalityTraitType;
import com.codecrew.personalitytest.restapi.exception.ResourceNotFoundException;
import com.codecrew.personalitytest.restapi.model.Answer;
import com.codecrew.personalitytest.restapi.model.PersonalityTrait;
import com.codecrew.personalitytest.restapi.model.Result;
import com.codecrew.personalitytest.restapi.model.UserAnswer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/{resultId}")
    public ResponseEntity<Result> getResultById(@PathVariable int resultId) throws ResourceNotFoundException {
        var result = resultRepository.findById(resultId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found with id" + resultId));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Result>> getResultsByPersonName(@PathVariable String name)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(resultRepository.findByPersonName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found with name" + name)));
    }

    @PostMapping
    public ResponseEntity<?> addResults(@RequestBody UserAnswer userAnswer) throws Exception {
        // if (userAnswer.getQuestionAnswers().size() < questionRepository.count()) {
        // throw new Exception("Some answers are missing!");
        // }
        var result = Result.builder()
                .personName(userAnswer.getName())
                .gender(userAnswer.getGender())
                .email(userAnswer.getEmail())
                .date(LocalDate.now())
                .isPublic(userAnswer.isPublic())
                .build();
        List<PersonalityTrait> traits = new ArrayList<>();

        PersonalityTraitGroup[] traitGroups = PersonalityTraitGroup.values();

        PersonalityTraitType[] traitTypes = PersonalityTraitType.values();

        int i = 0;

        for (var group : traitGroups) {
            var trait = PersonalityTrait.builder()
                    .totalPoint(0)
                    .personalityTraitGroup(group)
                    .personalityTraitType(traitTypes[i])
                    .build();

            var second_trait = PersonalityTrait.builder()
                    .totalPoint(0)
                    .personalityTraitGroup(group)
                    .personalityTraitType(traitTypes[i + 1])
                    .build();

            traits.addAll(List.of(trait, second_trait));
            i += 2;
        }

        userAnswer.getQuestionAnswers().stream().forEachOrdered(qa -> {
            var traitGroupQ = qa.getQuestion().getTraitGroup();
            PersonalityTraitType traitTypeQ;
            int point;
            if (qa.isQuestionAnswer()) {
                traitTypeQ = qa.getQuestion().getCaseTrue();
                point = qa.getQuestion().getCaseTruePoint();
            } else if (!qa.isQuestionAnswer()) {
                traitTypeQ = qa.getQuestion().getCaseFalse();
                point = qa.getQuestion().getCaseFalsePoint();
            }
            // else if if (!qa.getQuestion().getAnswerAlternative().isBlank() &&
            // qa.isAlternative())
            else {
                traitTypeQ = qa.getQuestion().getCaseAlternative();
                point = qa.getQuestion().getCaseAlternativePoint();
            }
            var trait = traits.stream()
                    .filter(t -> (t.getPersonalityTraitGroup() == traitGroupQ
                            && t.getPersonalityTraitType() == traitTypeQ))
                    .findFirst().get();

            trait.setTotalPoint(trait.getTotalPoint() + point);
        });
        traits.stream().forEach(trait -> {
            trait.setResult(result);
        });

        result.setTraits(traits);
        resultRepository.save(result);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateResult(@RequestBody Result result, @PathVariable int id) {
        // var old_result = resultRepository.findById(id).orElseThrow();
        // old_result.setTraits(result.getTraits());
        // old_result.setGender(result.getGender());
        // old_result.setPersonName(result.getPersonName());
        // resultRepository.save(old_result);

        // return ResponseEntity.ok(old_result);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteResult(@PathVariable int id) {
        var result = resultRepository.findById(id).orElseThrow();
        resultRepository.delete(result);
        return ResponseEntity.ok(result);
    }
}
