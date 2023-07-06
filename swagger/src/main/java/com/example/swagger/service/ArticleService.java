package com.example.swagger.service;

import com.example.swagger.dto.ArticleListResponseDto;
import com.example.swagger.dto.ArticleRequestDto;
import com.example.swagger.dto.ArticleResponseDto;
import com.example.swagger.entity.Article;
import com.example.swagger.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<ArticleListResponseDto> getArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(ArticleListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArticleResponseDto getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ArticleResponseDto(article);
    }

    @Transactional
    public void createArticle(ArticleRequestDto articleRequestDto) {
        Article article = articleRequestDto.toEntity();
        articleRepository.save(article);
    }

    @Transactional
    public void updateArticle(Long articleId, ArticleRequestDto articleRequestDto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        article.update(articleRequestDto.getTitle(), articleRequestDto.getTitle());
    }

    @Transactional
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        articleRepository.delete(article);
    }
}
