package com.example.querydsl.repository;

import com.example.querydsl.entity.Article;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.querydsl.entity.QArticle.article;

@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Article> searchAll(Pageable pageable, String title, String content) {
        List<Article> articles = queryFactory
                .selectFrom(article)
                .where(
                        titleContainsIgnoreCase(title),
                        contentContainsIgnoreCase(content)
                )
                .orderBy(article.createdAt.desc())
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(article.count())
                .from(article)
                .where(
                        titleContainsIgnoreCase(title),
                        contentContainsIgnoreCase(content)
                );

        return PageableExecutionUtils.getPage(articles, pageable, countQuery::fetchOne);
    }

    private BooleanExpression titleContainsIgnoreCase(String title) {
        return StringUtils.hasText(title) ? article.title.containsIgnoreCase(title) : null;
    }

    private BooleanExpression contentContainsIgnoreCase(String content) {
        return StringUtils.hasText(content) ? article.content.containsIgnoreCase(content) : null;
    }
}
