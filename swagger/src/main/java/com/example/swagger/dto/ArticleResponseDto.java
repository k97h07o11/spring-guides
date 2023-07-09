package com.example.swagger.dto;

import com.example.swagger.entity.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(title = "게시글 조회 응답")
@Getter
public class ArticleResponseDto {

    @Schema(description = "게시글 제목")
    private String title;

    @Schema(description = "게시글 내용")
    private String content;

    public ArticleResponseDto(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
