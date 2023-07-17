package com.example.querydsl.controller;

import com.example.querydsl.dto.ArticleListResponseDto;
import com.example.querydsl.dto.ArticleRequestDto;
import com.example.querydsl.dto.ArticleResponseDto;
import com.example.querydsl.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public List<ArticleListResponseDto> getArticles(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content
    ) {
        return articleService.getArticles(title, content);
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
