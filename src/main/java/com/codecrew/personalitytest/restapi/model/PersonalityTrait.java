package com.codecrew.personalitytest.restapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.codecrew.personalitytest.restapi.enums.PersonalityTraitGroup;
import com.codecrew.personalitytest.restapi.enums.PersonalityTraitType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalityTrait {
    @Id
    @GeneratedValue
    private int traitId;
    private int totalPoint;
    private PersonalityTraitGroup personalityTraitGroup;
    private PersonalityTraitType personalityTraitType;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "result_id")
    private Result result;
}
