package com.example.querydsl.dto;

import com.example.querydsl.entity.Article;
import lombok.Getter;

@Getter
public class ArticleResponseDto {

    private String title;

    private String content;

    public ArticleResponseDto(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
