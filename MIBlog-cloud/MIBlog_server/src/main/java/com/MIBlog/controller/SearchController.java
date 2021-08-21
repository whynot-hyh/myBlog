package com.MIBlog.controller;

import com.MIBlog.entity.PageArticleVO;
import com.MIBlog.entity.PageEntity;
import com.MIBlog.entity.ResponseWrapper;
import com.MIBlog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private ArticleService articleService;


    @Autowired
    public SearchController(ArticleService articleService) {

        this.articleService = articleService;
    }


    @PostMapping("/search")
    public ResponseWrapper<PageArticleVO> getSearch(@RequestBody PageEntity pageEntity){
        if (!StringUtils.isEmpty(pageEntity.getSearch()) &&  StringUtils.isEmpty(pageEntity.getNickname())){
           return ResponseWrapper.success(articleService.getSearch(pageEntity));
        }
        if (!StringUtils.isEmpty(pageEntity.getNickname()) && StringUtils.isEmpty(pageEntity.getSearch())){
            return ResponseWrapper.success(articleService.getUserArticle(pageEntity));
        }else {
            return ResponseWrapper.success(articleService.getUserAndTitleSearch(pageEntity));
        }
    }
}
