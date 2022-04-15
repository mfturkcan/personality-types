package com.codecrew.personalitytest.restapi.model;

import com.codecrew.personalitytest.restapi.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class Answer {
    private String personName;
    private Gender gender;
    private List<Boolean> answers;
}
