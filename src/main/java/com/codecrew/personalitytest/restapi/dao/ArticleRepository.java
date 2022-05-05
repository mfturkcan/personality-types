package com.codecrew.personalitytest.restapi.dao;

import com.codecrew.personalitytest.restapi.model.Article;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

}
