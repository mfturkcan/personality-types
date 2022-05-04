package com.codecrew.personalitytest.restapi.model;

import java.io.Serializable;
import java.util.List;

import com.codecrew.personalitytest.restapi.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswer implements Serializable {
    private String name;
    private String email;
    private Gender gender;
    private boolean publicResult;
    private List<Answer> questionAnswers;
}
