package com.example.querydsl.repository;

import com.example.querydsl.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {

    Page<Article> searchAll(Pageable pageable, String title, String content);
}
