package com.example.querydsl.repository;

import com.example.querydsl.entity.Article;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.querydsl.entity.QArticle.article;

@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Article> searchAll(String title, String content) {
        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(title)) {
            builder.and(article.title.contains(title));
        }

        if (StringUtils.hasText(content)) {
            builder.and(article.content.contains(content));
        }

        return queryFactory
                .selectFrom(article)
                .where(builder)
                .fetch();
    }
}
