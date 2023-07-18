package com.example.exception.dto;

import com.example.exception.entity.Article;
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
