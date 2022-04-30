package com.codecrew.personalitytest.restapi.model;

import java.io.Serializable;

import org.springframework.boot.jackson.JsonComponent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer extends BasedEntity implements Serializable {
    private boolean questionAnswer; // 1,0
    private Question question;
}
