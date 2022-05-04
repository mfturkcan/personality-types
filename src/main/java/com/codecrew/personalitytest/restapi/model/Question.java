package com.codecrew.personalitytest.restapi.model;

import com.codecrew.personalitytest.restapi.enums.PersonalityTraitGroup;
import com.codecrew.personalitytest.restapi.enums.PersonalityTraitType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String question; // TODO: make variable name questionTitle
    private int questionNumber;
    private short caseTruePoint;
    private short caseFalsePoint;
    private short caseAlternativePoint;
    private PersonalityTraitGroup traitGroup;
    private String answerTrue;
    private String answerFalse;
    private String answerAlternative;
    private PersonalityTraitType caseTrue;
    private PersonalityTraitType caseFalse;
    private PersonalityTraitType caseAlternative;

    public String toJson() throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(this);
    }
}
