package com.example.swagger.dto;

import com.example.swagger.entity.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(title = "게시글 목록 조회 응답")
@Getter
public class ArticleListResponseDto {

    @Schema(description = "게시글 ID")
    private Long id;

    @Schema(description = "게시글 제목")
    private String title;

    public ArticleListResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
    }
}
