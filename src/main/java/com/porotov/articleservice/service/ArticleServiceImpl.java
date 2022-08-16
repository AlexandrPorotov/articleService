package com.porotov.articleservice.service;

import com.porotov.articleservice.DTO.articleDTO.ArticleCreationDTO;
import com.porotov.articleservice.DTO.articleDTO.ArticleDTO;
import com.porotov.articleservice.DTO.articleDTO.ArticleMapper;
import com.porotov.articleservice.DTO.articleDTO.ArticleStatusDTO;
import com.porotov.articleservice.model.Article;
import com.porotov.articleservice.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final   ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;



    @Override
    public Optional<ArticleDTO> saveArticle(ArticleCreationDTO articleCreationDTO) {

        Article article = articleMapper.toArticle(articleCreationDTO);
        Optional<Article> articleFromDB = articleRepository.findArticleByUrl(article.getUrl());

        if(articleFromDB.isPresent()){
            articleFromDB = Optional.empty();
        } else {
            articleFromDB = Optional.of(articleRepository.save(article));
        }

        return articleFromDB.map(articleMapper::toDTO);

    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        return articleRepository.findAll().stream().map(articleMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<ArticleDTO> getArticleByID(Long id) {

        Optional<ArticleDTO> articleFromDB = articleRepository.findById(id).map(articleMapper::toDTO);

        if(articleFromDB.isEmpty()){
            articleFromDB = Optional.empty();
        } else {
            Article article = articleMapper.toArticle(articleFromDB.get());
            article.setDateTime(LocalDateTime.now());
            article.setCounter(article.getCounter()+1);
            article.setMarker(true);
        }

        return articleFromDB;
    }

    @Override
    public ArticleDTO updateArticle(ArticleDTO updatedArticleDTO) {
        return articleMapper.toDTO(articleRepository.updateArticle(updatedArticleDTO.getUrl(),updatedArticleDTO.getTitle(),updatedArticleDTO.getId()));
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<ArticleDTO> saveAllArticles(List<ArticleCreationDTO> articleCreationDTOList) {

        List<ArticleDTO> outPutList = new ArrayList<>();

        for(ArticleCreationDTO articleCreationDTO : articleCreationDTOList) {
            Optional<Article> savedArticle = articleRepository.findArticleByUrl(articleCreationDTO.getUrl());
            if(savedArticle.isEmpty()){
                Article article = articleMapper.toArticle(articleCreationDTO);
                articleRepository.save(article);
                ArticleDTO articleDTO = articleMapper.toDTO(article);
                articleDTO.setStatus(ArticleStatusDTO.CREATED);
                outPutList.add(articleDTO);
            } else {
                ArticleDTO articleDTO = articleMapper.toDTO(savedArticle.get());
                articleDTO.setStatus(ArticleStatusDTO.ALREADY_EXIST);
                outPutList.add(articleDTO);
            }
        }

        return outPutList;
    }

    @Override
    public Optional<ArticleDTO> getRecentArticle() {

        Article recentArticle = articleRepository.getRecentArticle().get(0);

        recentArticle.setDateTime(LocalDateTime.now());
        recentArticle.setCounter(recentArticle.getCounter()+1);
        recentArticle.setMarker(true);

        articleRepository.save(recentArticle);

        return Optional.of(articleMapper.toDTO(recentArticle));

    }

    @Override
    public Optional<ArticleDTO> getArticleByUrl(String url)  {

        Optional<ArticleDTO> responseArticle = articleRepository.findArticleByUrl(url).map(articleMapper::toDTO);

        if(responseArticle.isEmpty()) {
            responseArticle = Optional.empty();
        }

        return responseArticle ;
    }
}
