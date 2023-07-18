package com.example.exception.controller;

import com.example.exception.dto.ArticleListResponseDto;
import com.example.exception.dto.ArticleRequestDto;
import com.example.exception.dto.ArticleResponseDto;
import com.example.exception.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public Page<ArticleListResponseDto> getArticles(
            Pageable pageable
    ) {
        return articleService.getArticles(pageable);
    }

    @GetMapping("/articles/{articleId}")
    public ArticleResponseDto getArticle(
            @PathVariable Long articleId
    ) {
        return articleService.getArticle(articleId);
    }

    @PostMapping("/articles")
    public void postArticle(
            @RequestBody ArticleRequestDto articleRequestDto
    ) {
        articleService.createArticle(articleRequestDto);
    }

    @PutMapping("/articles/{articleId}")
    public void putArticle(
            @PathVariable Long articleId,
            @RequestBody ArticleRequestDto articleRequestDto
    ) {
        articleService.updateArticle(articleId, articleRequestDto);
    }

    @DeleteMapping("/articles/{articleId}")
    public void deleteArticle(
            @PathVariable Long articleId
    ) {
        articleService.deleteArticle(articleId);
    }
}
