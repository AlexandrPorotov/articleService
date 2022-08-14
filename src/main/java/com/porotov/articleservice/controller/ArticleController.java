package com.porotov.articleservice.controller;

import com.porotov.articleservice.DTO.ArticleDTO.ArticleCreationDTO;
import com.porotov.articleservice.DTO.ArticleDTO.ArticleDTO;
import com.porotov.articleservice.DTO.ArticleDTO.ArticleIdDTO;
import com.porotov.articleservice.DTO.ArticleDTO.ArticleMapper;
import com.porotov.articleservice.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**@author Alexandr Porotov
 * @version 1.0.0 pre-alpha
 * */
@RestController
@RequestMapping("articles/")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleMapper articleMapper;

    @GetMapping("recent")
    public ArticleDTO getIdRecentArticle() {
        return articleMapper.toDTO(articleService.getIdRecentArticle());
    }

    /**@param url String
     * <p>GET- запрос. Получает параметром строку url, передает в ArticleService. Возвращает id записи в БД.
     * Поиск происходит по полю Article.url</p>
     * @see ArticleIdDTO
     * @see ArticleService*/
    @GetMapping(params = {"url"})
    public ArticleIdDTO getArticleIdByUrl(@RequestParam(value = "url") String url) {
        return new ArticleIdDTO(articleService.getArticleIdByUrl(url));
    }

    /**
     * @param articleCreationDTOList List
     * <p>POST - запрос. Получает на вход JSON Array. Передает в ArticleService для записи данных в БД.
     *  В ArticleService производится проверка на уникальность по полю: Article.url</p>
     * @see ArticleService
     * @see ArticleCreationDTO
     * @see com.porotov.articleservice.model.Article
     * */
    @PostMapping
    public void saveArticles(@RequestBody List<ArticleCreationDTO> articleCreationDTOList) {
        articleService.saveArticles(articleCreationDTOList);
    }

    //*изменение записи с обновлением даты
    @PutMapping(params = {"id","field"})
    public void changeArticle(HttpEntity<String> httpEntity, @RequestParam(value = "id") Long id, @RequestParam(value = "field") String field) {
    }

}
