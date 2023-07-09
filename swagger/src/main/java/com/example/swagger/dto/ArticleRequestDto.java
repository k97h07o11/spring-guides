package com.example.swagger.dto;

import com.example.swagger.entity.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Schema(title = "게시글 작성 및 수정 요청")
@Getter
public class ArticleRequestDto {

    @Schema(description = "게시글 제목")
    @NotBlank
    private String title;

    @Schema(description = "게시글 내용")
    @NotBlank
    private String content;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
