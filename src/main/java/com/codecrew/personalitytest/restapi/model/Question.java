package com.codecrew.personalitytest.restapi.model;

import com.codecrew.personalitytest.restapi.enums.PersonalityTraitGroup;
import com.codecrew.personalitytest.restapi.enums.PersonalityTraitType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.springframework.boot.jackson.JsonComponent;

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
@JsonComponent
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "question", columnDefinition = "LONGTEXT")
    private String question; // TODO: make variable name questionTitle
    private short point;
    private PersonalityTraitGroup traitGroup;
    private String answerTrue;
    private String answerFalse;
    private PersonalityTraitType caseTrue;
    private PersonalityTraitType caseFalse;

    public String toJson() throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(this);
    }
}
