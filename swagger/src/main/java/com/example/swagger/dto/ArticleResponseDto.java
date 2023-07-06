package com.example.swagger.dto;

import com.example.swagger.entity.Article;
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
