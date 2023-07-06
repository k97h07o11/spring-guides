package com.example.swagger.controller;

import com.example.swagger.dto.ArticleListResponseDto;
import com.example.swagger.dto.ArticleRequestDto;
import com.example.swagger.dto.ArticleResponseDto;
import com.example.swagger.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public List<ArticleListResponseDto> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping("/articles/{articleId}")
    public ArticleResponseDto getArticle(
            @PathVariable Long articleId
    ) {
        return articleService.getArticle(articleId);
    }

    @PostMapping("/articles")
    public void postArticle(
            @RequestBody @Valid ArticleRequestDto articleRequestDto
    ) {
        articleService.createArticle(articleRequestDto);
    }

    @PutMapping("/articles/{articleId}")
    public void putArticle(
            @PathVariable Long articleId,
            @RequestBody @Valid ArticleRequestDto articleRequestDto
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
