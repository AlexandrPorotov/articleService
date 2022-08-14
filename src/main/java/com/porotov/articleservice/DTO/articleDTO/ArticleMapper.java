package com.porotov.articleservice.DTO.articleDTO;

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

    public ArticleIdDTO toIdDTO(Article article) {
        return new ArticleIdDTO(article.getId());
    }

    public ArticleIdDTO dtoToIdDto(ArticleDTO articleDTO){
        return new ArticleIdDTO(articleDTO.getId());
    }

    public Article toArticle(ArticleDTO articleDTO){

        Article article = new Article();

        article.setTitle(articleDTO.getTitle());
        article.setUrl(articleDTO.getUrl());

        return article;
    }

    public Article toArticle(ArticleCreationDTO articleCreateDTO){

        Article article = new Article();

        article.setTitle(articleCreateDTO.getTitle());
        article.setUrl(articleCreateDTO.getUrl());

        return article;

    }

}
