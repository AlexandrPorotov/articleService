package com.porotov.articleservice.DTO.ArticleDTO;

import com.porotov.articleservice.model.Article;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ArticleMapper {

    public ArticleDTO toDTO(Article article){

        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setUrl(article.getUrl());
        articleDTO.setDateTime(article.getDateTime());

        return articleDTO;

    }

    public Article toArticle(ArticleCreationDTO articleCreateDTO){

        Article article = new Article();

        article.setTitle(articleCreateDTO.getTitle());
        article.setUrl(articleCreateDTO.getUrl());

        return article;

    }

}
