package com.porotov.articleservice.service;

import com.porotov.articleservice.model.Article;
import com.porotov.articleservice.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    ArticleService articleService;
    @Mock
    ArticleRepository articleRepository;


    @Test
    void getArticleById() {
        Article articleTest = new Article();
        articleRepository.save(articleTest);

        Optional<Article> article = Optional.of(articleRepository.getReferenceById(1L));
        assertTrue(article.isPresent());


    }
}