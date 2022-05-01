package com.codecrew.personalitytest.restapi.dao;

import com.codecrew.personalitytest.restapi.model.PersonalityTrait;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalityTraitRepository extends JpaRepository<PersonalityTrait, Integer> {

}
