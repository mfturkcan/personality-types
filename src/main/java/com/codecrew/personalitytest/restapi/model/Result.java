package com.codecrew.personalitytest.restapi.model;

import com.codecrew.personalitytest.restapi.enums.Gender;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue
    private int id;
    private String personName;
    private Gender gender;
    private String email;
    private boolean isPublic;
    private LocalDate date;

    @JsonManagedReference
    @OneToMany(mappedBy = "result", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<PersonalityTrait> traits;
}
