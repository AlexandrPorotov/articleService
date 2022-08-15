package com.porotov.articleservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.porotov.articleservice.DTO.articleDTO.ArticleCreationDTO;
import com.porotov.articleservice.DTO.articleDTO.ArticleMapper;
import com.porotov.articleservice.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@SpringBootTest
class ArticleControllerMockMvcIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ArticleMapper articleMapper;


    @Test
    void saveArticle() throws Exception {

        ArticleCreationDTO articleCreationDTO = new ArticleCreationDTO();
        articleCreationDTO.setTitle("test - saveArticle" + Math.random()*1);
        articleCreationDTO.setUrl("test - saveArticle" + Math.random()*1);

        mockMvc.perform(
                post("/api/articles/one")
                        .content(objectMapper.writeValueAsString(articleCreationDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(articleCreationDTO.getTitle()))
                .andExpect(jsonPath("$.url").value(articleCreationDTO.getUrl()));

    }

    @Test
    void getRecentArticle() {

    }

    @Test
    void getArticleIdByUrl() {

        ArticleCreationDTO articleCreationDTO = new ArticleCreationDTO();
        articleCreationDTO.setTitle("test - getArticleIdByUrl" + Math.random()*1);
        articleCreationDTO.setUrl("test - getArticleIdByUrl" + Math.random()*1);

        Long id = articleRepository.save(articleMapper.toArticle(articleCreationDTO)).getId();

    }

    @Test
    void saveArticles() {
    }

    @Test
    void getAllArticles() {
    }

    @Test
    void getArticleById() throws Exception {

        ArticleCreationDTO articleCreationDTO = new ArticleCreationDTO();
        articleCreationDTO.setTitle("test - getArticleById" + Math.random()*1);
        articleCreationDTO.setUrl("test - getArticleById" + Math.random()*1);

        Long id = articleRepository.save(articleMapper.toArticle(articleCreationDTO)).getId();

        mockMvc.perform(
                get("/api/articles/{id}",id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value(articleCreationDTO.getTitle()))
                .andExpect(jsonPath("$.url").value(articleCreationDTO.getUrl()));


    }

    @Test
    void changeArticle() {
    }

    @Test
    void deleteArticle() {
    }
}