package com.porotov.articleservice.DTO.articleDTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDTO {

    private Long id;

    private String title;

    private String url;

    private LocalDateTime dateTime;

    private ArticleStatusDTO status;

}
