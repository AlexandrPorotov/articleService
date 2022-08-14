package com.porotov.articleservice.service;

import com.porotov.articleservice.DTO.ArticleDTO.ArticleCreationDTO;
import com.porotov.articleservice.DTO.ArticleDTO.ArticleDTO;
import com.porotov.articleservice.DTO.ArticleDTO.ArticleIdDTO;
import com.porotov.articleservice.DTO.ArticleDTO.ArticleMapper;
import com.porotov.articleservice.model.Article;
import com.porotov.articleservice.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public Article getIdRecentArticle() {
        Article recentArticle = articleRepository.getRecentArticle().get(0);
        recentArticle.setDateTime(LocalDateTime.now());
        recentArticle.setCounter(recentArticle.getCounter()+1);
        recentArticle.setMarker(true);
        articleRepository.save(recentArticle);
        return recentArticle;
    }

    public Long getArticleIdByUrl(String url){
        Optional<Article> articleFromDB = articleRepository.getArticleByUrl(url);
        return articleFromDB.map(Article::getId).orElse(null);
    }

    public void saveArticles(List<ArticleCreationDTO> articleCreationDTOList) {

        for(ArticleCreationDTO articleCreationDTO : articleCreationDTOList) {
            Article article = articleMapper.toArticle(articleCreationDTO);
            if(articleRepository.getArticleByUrl(articleCreationDTO.getUrl()).isEmpty()) {
                article.setMarker(false);
                article.setCounter(0L);
                article.setDateTime(LocalDateTime.now());
                articleRepository.save(article);
            } else {
                System.out.println("Article already exist");
            }
        }
    }

}
