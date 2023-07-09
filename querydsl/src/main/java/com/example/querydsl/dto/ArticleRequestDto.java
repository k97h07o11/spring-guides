package com.example.querydsl.dto;

import com.example.querydsl.entity.Article;
import lombok.Getter;

@Getter
public class ArticleRequestDto {

    private String title;

    private String content;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
