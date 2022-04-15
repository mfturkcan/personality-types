package com.codecrew.personalitytest.restapi.dao;

import com.codecrew.personalitytest.restapi.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Integer> {
    Optional<List<Result>> findByPersonName(String personName);
}
