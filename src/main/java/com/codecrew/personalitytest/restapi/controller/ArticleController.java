package com.codecrew.personalitytest.restapi.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.codecrew.personalitytest.restapi.dao.ArticleRepository;
import com.codecrew.personalitytest.restapi.dto.ArticleDto;
import com.codecrew.personalitytest.restapi.exception.ResourceNotFoundException;
import com.codecrew.personalitytest.restapi.model.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllByDate() {
        var articles = articleRepository.findAll(Sort.by("publishDate"));

        return ResponseEntity.ok(articles);
    }

    @PostMapping
    public ResponseEntity<Article> addArticle(@RequestBody ArticleDto articleDto) throws IOException {

        var article = Article.builder()
                .author(articleDto.getAuthor())
                .title(articleDto.getTitle())
                .text(articleDto.getText())
                .publishDate(LocalDate.now())
                .image(articleDto.getImage())
                .build();

        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Article> deleteArticle(@PathVariable int articleId) throws ResourceNotFoundException {
        var article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id " + articleId));

        articleRepository.delete(article);
        return ResponseEntity.ok(article);
    }
}
