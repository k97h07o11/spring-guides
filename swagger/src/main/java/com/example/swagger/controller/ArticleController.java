package com.example.swagger.controller;

import com.example.swagger.dto.ArticleListResponseDto;
import com.example.swagger.dto.ArticleRequestDto;
import com.example.swagger.dto.ArticleResponseDto;
import com.example.swagger.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Article", description = "게시글 CRUD API")
@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @Operation(summary = "게시글 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ArticleListResponseDto.class))))
    })
    @GetMapping("/articles")
    public List<ArticleListResponseDto> getArticles() {
        return articleService.getArticles();
    }

    @Operation(summary = "게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 조회 성공", content = @Content(schema = @Schema(implementation = ArticleResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "조회하려는 게시글이 존재하지 않는 경우", content = @Content)
    })
    @GetMapping("/articles/{articleId}")
    public ArticleResponseDto getArticle(
            @Parameter(description = "게시글 ID")
            @PathVariable Long articleId
    ) {
        return articleService.getArticle(articleId);
    }

    @Operation(summary = "게시글 작성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 작성 성공", content = @Content)
    })
    @PostMapping("/articles")
    public void postArticle(
            @RequestBody @Valid ArticleRequestDto articleRequestDto
    ) {
        articleService.createArticle(articleRequestDto);
    }

    @Operation(summary = "게시글 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 수정 성공", content = @Content),
            @ApiResponse(responseCode = "400", description = "수졍하려는 게시글이 존재하지 않는 경우", content = @Content)
    })
    @PutMapping("/articles/{articleId}")
    public void putArticle(
            @Parameter(description = "게시글 ID")
            @PathVariable Long articleId,
            @RequestBody @Valid ArticleRequestDto articleRequestDto
    ) {
        articleService.updateArticle(articleId, articleRequestDto);
    }

    @Operation(summary = "게시글 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 삭제 성공", content = @Content),
            @ApiResponse(responseCode = "400", description = "삭제하려는 게시글이 존재하지 않는 경우", content = @Content)
    })
    @DeleteMapping("/articles/{articleId}")
    public void deleteArticle(
            @Parameter(description = "게시글 ID")
            @PathVariable Long articleId
    ) {
        articleService.deleteArticle(articleId);
    }
}
