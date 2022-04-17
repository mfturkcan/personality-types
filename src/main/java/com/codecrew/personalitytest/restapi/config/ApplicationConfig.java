package com.codecrew.personalitytest.restapi.config;

import com.codecrew.personalitytest.restapi.model.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfig {
    @Bean
    @Scope(value = "singleton")
    public Answer answer(){
        return new Answer();
    }
}
