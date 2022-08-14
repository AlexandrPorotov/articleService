package com.porotov.articleservice.repository;

import com.porotov.articleservice.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT article FROM Article article ORDER BY article.counter ASC, article.dateTime DESC")
    List<Article> getRecentArticle();

    @Query("SELECT article FROM Article article WHERE article.title = ?1")
    Article getArticleByTitle(String title);

    @Query("SELECT article FROM Article article WHERE article.url = ?1")
    Optional<Article> getArticleByUrl(String url);

    @Transactional(propagation = Propagation.REQUIRED)
    @Modifying
    @Query("UPDATE Article article SET article.title = ?1 , article.dateTime = current_date WHERE article.id = ?2")
    void updateArticleTitleById(String title, Long id);

    @Transactional(propagation = Propagation.REQUIRED)
    @Modifying
    @Query("UPDATE Article article SET article.url = ?1 , article.dateTime = current_date WHERE article.id = ?2")
    void updateArticleUrlById(String url, Long id);

}