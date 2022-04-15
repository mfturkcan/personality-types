package com.codecrew.personalitytest.restapi.model;

import com.codecrew.personalitytest.restapi.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class Result {
    @Id @GeneratedValue
    private int id;
    private String personName;
    private Gender gender;
    @OneToMany(mappedBy = "result")
    private List<PersonalityTrait> traits;
}
