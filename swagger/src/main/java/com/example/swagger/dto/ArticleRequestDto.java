package com.example.swagger.dto;

import com.example.swagger.entity.Article;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ArticleRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
