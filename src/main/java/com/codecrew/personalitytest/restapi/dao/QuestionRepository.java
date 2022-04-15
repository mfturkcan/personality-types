package com.codecrew.personalitytest.restapi.dao;

import com.codecrew.personalitytest.restapi.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findByQuestionNumber(short questionNumber);
}
