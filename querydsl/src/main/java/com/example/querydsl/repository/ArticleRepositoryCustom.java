package com.example.querydsl.repository;

import com.example.querydsl.entity.Article;

import java.util.List;

public interface ArticleRepositoryCustom {

    List<Article> searchAll(String title, String content);
}
