package com.codecrew.personalitytest.restapi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        run(Application.class, args);

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // TODO Auto-generated method stub
                WebMvcConfigurer.super.addCorsMappings(registry);
                registry.addMapping("/**").allowedOrigins("http://localhost:4200",
                        "https://personality-test-g2.netlify.app/");
            }
        };
    }

    /*
     * General Project Plan
     * Article
     * Question
     * Security? if added with jwt
     * Result -> 4 models with enum type and their total point
     * Question's type enums, such as
     * Questions will be added manually because they are static
     * Answer -> question number + answer
     * Exception handler will be added
     * Validation for question & answers
     */

    /*
     * Question model sample
     * personalityTraitGroup -> Mind
     * question -> text
     * caseTrue -> personalityTrait (Introvert)
     * caseFalse -> personalityTrait (Extravert)
     * point -> short int (2)
     */

    /*
     * Answer
     * personName -> text
     * gender -> GENDER ENUM
     * list (short) -> [0,1,2,3,4] that matches with each question
     */

    /*
     * Result (will be stored depends on Answer model)
     * personName -> text
     * gender -> GENDER ENUM
     * list (Personality Trait)
     */

    /*
     * Personality Trait class model
     * personalityTraitGroup -> enum
     * personalityTraitType -> enum
     * point -> short
     */

    // Saved Results can be added to as total result to home page
}
