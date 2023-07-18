package com.example.exception.dto;

import com.example.exception.entity.Article;
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
