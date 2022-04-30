package com.codecrew.personalitytest.restapi.model;

import com.codecrew.personalitytest.restapi.enums.Gender;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.ArrayList;
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

    @Type(type = "json")
    @Column(name = "answers", columnDefinition = "json")
    private List<Answer> answers = new ArrayList<>();
}
