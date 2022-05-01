package com.codecrew.personalitytest.restapi.controller;

import com.codecrew.personalitytest.restapi.dao.QuestionRepository;
import com.codecrew.personalitytest.restapi.dao.ResultRepository;
import com.codecrew.personalitytest.restapi.enums.PersonalityTraitGroup;
import com.codecrew.personalitytest.restapi.enums.PersonalityTraitType;
import com.codecrew.personalitytest.restapi.model.Answer;
import com.codecrew.personalitytest.restapi.model.PersonalityTrait;
import com.codecrew.personalitytest.restapi.model.Result;
import com.codecrew.personalitytest.restapi.model.UserAnswer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

        var traitGroups = PersonalityTraitGroup.values();
        var traitTypes = PersonalityTraitType.values();

        for (int i = 0; i < traitGroups.length; i++) {
            var trait = PersonalityTrait.builder()
                    .totalPoint(0)
                    .personalityTraitGroup(traitGroups[i])
                    .personalityTraitType(traitTypes[i])
                    .build();

            var second_trait = PersonalityTrait.builder()
                    .totalPoint(0)
                    .personalityTraitGroup(traitGroups[i])
                    .personalityTraitType(traitTypes[i + i])
                    .build();

            traits.addAll(List.of(trait, second_trait));
        }

        userAnswer.getQuestionAnswers().stream().forEachOrdered(qa -> {
            var traitGroupQ = qa.getQuestion().getTraitGroup();
            PersonalityTraitType traitTypeQ;
            if (qa.isQuestionAnswer())
                traitTypeQ = qa.getQuestion().getCaseTrue();
            else
                traitTypeQ = qa.getQuestion().getCaseFalse();

            var trait = traits.stream()
                    .filter(t -> (t.getPersonalityTraitGroup() == traitGroupQ
                            && t.getPersonalityTraitType() == traitTypeQ))
                    .findFirst().get();
            trait.setTotalPoint(trait.getTotalPoint() + qa.getQuestion().getPoint());
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
