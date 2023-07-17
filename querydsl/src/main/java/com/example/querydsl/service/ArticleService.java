package com.example.querydsl.service;

import com.example.querydsl.dto.ArticleListResponseDto;
import com.example.querydsl.dto.ArticleRequestDto;
import com.example.querydsl.dto.ArticleResponseDto;
import com.example.querydsl.entity.Article;
import com.example.querydsl.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleListResponseDto> getArticles(Pageable pageable, String title, String content) {
        Page<Article> articles = articleRepository.searchAll(pageable, title, content);
        return articles.map(ArticleListResponseDto::new);
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
