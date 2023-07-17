package com.example.querydsl.repository;

import com.example.querydsl.entity.Article;
import com.querydsl.core.types.dsl.BooleanExpression;
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
        return queryFactory
                .selectFrom(article)
                .where(
                        titleContains(title),
                        contentContains(content)
                )
                .orderBy(article.createdAt.desc())
                .fetch();
    }

    private BooleanExpression titleContains(String title) {
        return StringUtils.hasText(title) ? article.title.contains(title) : null;
    }

    private BooleanExpression contentContains(String content) {
        return StringUtils.hasText(content) ? article.content.contains(content) : null;
    }
}
