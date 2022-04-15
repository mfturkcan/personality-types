package com.codecrew.personalitytest.restapi.model;

import com.codecrew.personalitytest.restapi.enums.PersonalityTraitGroup;
import com.codecrew.personalitytest.restapi.enums.PersonalityTraitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class PersonalityTrait {
    @Id @GeneratedValue
    private int id;
    private short questionNumber;
    private PersonalityTraitGroup traitGroup;
    private PersonalityTraitType traitType;
    private short point;

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "result_id", nullable = false)
    private Result result;
}
