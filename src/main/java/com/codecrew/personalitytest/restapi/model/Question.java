package com.codecrew.personalitytest.restapi.model;

import com.codecrew.personalitytest.restapi.enums.PersonalityTraitGroup;
import com.codecrew.personalitytest.restapi.enums.PersonalityTraitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "question", columnDefinition = "LONGTEXT")
    private String question;
    private short questionNumber;
    private short point;
    private PersonalityTraitGroup traitGroup;
    private PersonalityTraitType caseTrue;
    private PersonalityTraitType caseFalse;
}
