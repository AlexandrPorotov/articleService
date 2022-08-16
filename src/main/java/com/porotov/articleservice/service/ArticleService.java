package com.porotov.articleservice.service;

import com.porotov.articleservice.DTO.articleDTO.ArticleCreationDTO;
import com.porotov.articleservice.DTO.articleDTO.ArticleDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author Alexandr Porotov
 * @version pre-alpha
 * <p>Интерфейс с операциями CRUD и опциональными методами -
 * <ul>
 *     <li>saveAllArticles - сохраняет переданный список объектов Articles</li>
 *     <li>getRecentArticles - получение самой "свежей статьи"</li>
 *     <li>getArticleIdByUrl - получение id статьи по полю Article.url</li>
 * </ul></p>
 * @see ArticleServiceImpl
 */
public interface ArticleService {

    //CRUD
    Optional<ArticleDTO> saveArticle(ArticleCreationDTO articleCreationDTO);
    List<ArticleDTO> getAllArticles();
    Optional<ArticleDTO> getArticleByID(Long id);
    ArticleDTO updateArticle(ArticleDTO updatedArticleDTO);
    void deleteArticle(Long id);

    //Custom
    List<ArticleDTO> saveAllArticles(List<ArticleCreationDTO> articleCreationDTOList);
    Optional<ArticleDTO> getRecentArticle();
    Optional<ArticleDTO> getArticleByUrl(String url);

}
