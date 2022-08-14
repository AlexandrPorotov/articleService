package com.porotov.articleservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "article_title")
    private String title;

    @Column(name = "article_url")
    private String url;

    @Column(name = "article_date")
    private LocalDateTime dateTime;

    @Column(name = "article_counter")
    private Long counter;

    @Column(name = "article_marker")
    private Boolean marker;

}
