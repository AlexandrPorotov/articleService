package com.porotov.articleservice.controller;

import com.porotov.articleservice.DTO.articleDTO.ArticleCreationDTO;
import com.porotov.articleservice.DTO.articleDTO.ArticleDTO;
import com.porotov.articleservice.DTO.articleDTO.ArticleIdDTO;
import com.porotov.articleservice.DTO.articleDTO.ArticleMapper;
import com.porotov.articleservice.service.ArticleServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**@author Alexandr Porotov
 * @version 1.0.0 pre-alpha
 * @see ArticleServiceImpl
 * @see ArticleMapper
 * */
@RestController
@RequestMapping("api/articles/")
@AllArgsConstructor
public class ArticleController {

    private final ArticleServiceImpl articleService;
    private final ArticleMapper articleMapper;

    /**<p>GET - запрос. Возвращает Article, выборка происходит по полям Article.dataTime и Article.counter</p>*/
    @GetMapping("recent")
    public ResponseEntity<ArticleDTO> getRecentArticle() {
        return articleService.getRecentArticle()
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    /**@param url String
     * <p>GET- запрос. Получает параметром строку url, передает в ArticleService. Возвращает id записи в БД.
     * Поиск происходит по полю Article.url</p>
     * @see ArticleIdDTO
     * @see ArticleServiceImpl */
    @GetMapping(params = {"url"})
    public ResponseEntity<ArticleIdDTO> getArticleIdByUrl(@RequestParam(value = "url") String url)  {

        return articleService.getArticleByUrl(url)
                .map(articleMapper::dtoToIdDto)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    /**
     * @param articleCreationDTOList List
     * <p>POST - запрос. Получает на вход JSON Array. Передает в ArticleService для записи данных в БД.
     *  В ArticleService производится проверка на уникальность по полю: Article.url</p>
     * @see ArticleServiceImpl
     * @see ArticleCreationDTO
     * @see com.porotov.articleservice.model.Article
     * */
    @PostMapping("all")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ArticleDTO> saveArticles(@RequestBody List<ArticleCreationDTO> articleCreationDTOList) {
        return articleService.saveAllArticles(articleCreationDTOList);
    }

    /**@param articleCreationDTO ArticleCreationDTO
     * <p>></p>*/
    @PostMapping("one")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ArticleDTO> saveArticle(@RequestBody ArticleCreationDTO articleCreationDTO) {
        Optional<ArticleDTO> savedArticle = articleService.saveArticle(articleCreationDTO);
        return savedArticle
                .map(articleDTO -> new ResponseEntity<>(articleDTO, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @GetMapping
    public List<ArticleDTO> getAllArticles(){
        return articleService.getAllArticles();
    }

    @GetMapping({"{id}"})
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable("id") Long id) {
        return articleService.getArticleByID(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}") // сохраняет новую запись, а не исправляет старую (исправить)
    public ResponseEntity<ArticleDTO> changeArticle(@PathVariable("id") Long id, @RequestBody ArticleDTO articleDTO) {
        return articleService.getArticleByID(id).map(savedArticle -> {
            savedArticle.setTitle(articleDTO.getTitle());
            savedArticle.setUrl(articleDTO.getUrl());

            ArticleDTO updatedArticleDTO = articleService.updateArticle(savedArticle);
            return new ResponseEntity<>(updatedArticleDTO, HttpStatus.OK);
        })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable("id") Long id){

        articleService.deleteArticle(id);

        return new ResponseEntity<>("Article deleted successfully!", HttpStatus.OK);

    }

}
