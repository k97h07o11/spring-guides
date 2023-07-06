package com.example.swagger.dto;

import com.example.swagger.entity.Article;
import lombok.Getter;

@Getter
public class ArticleListResponseDto {

    private Long id;

    private String title;

    public ArticleListResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
    }
}
