package com.example.querydsl.dto;

import com.example.querydsl.entity.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleListResponseDto {

    private Long id;

    private String title;

    private LocalDateTime createdAt;

    public ArticleListResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.createdAt = article.getCreatedAt();
    }
}
