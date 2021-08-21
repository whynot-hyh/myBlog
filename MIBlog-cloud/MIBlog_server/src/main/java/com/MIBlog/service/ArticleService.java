package com.MIBlog.service;

import com.MIBlog.VO.ArticleVO;
import com.MIBlog.dataobject.Article;
import com.MIBlog.entity.PageArticleVO;
import com.MIBlog.entity.PageEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ArticleService{
    int insert(Article article);

    PageArticleVO getArticleList(PageEntity pageEntity);

    PageArticleVO getList(PageEntity pageEntity);

    PageArticleVO getSearch(PageEntity pageEntity);

    PageArticleVO getTypeArticle(PageEntity pageEntity);

    PageArticleVO getArticleView();

    PageArticleVO get10Article();

    List<ArticleVO> getTops();

    List<Integer> getDataViews();

    ArticleVO detail(Integer id);

    ArticleVO cat(Integer id);

    Article getArticleById(Integer id);
    PageArticleVO getUserAndTitleSearch(PageEntity pageEntity);

    PageArticleVO getUserArticle(PageEntity pageEntity);

    int update(Article article);

    Integer count();

    void delete(Integer id);
}
