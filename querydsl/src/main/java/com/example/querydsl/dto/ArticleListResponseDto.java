package com.example.querydsl.dto;

import com.example.querydsl.entity.Article;
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
