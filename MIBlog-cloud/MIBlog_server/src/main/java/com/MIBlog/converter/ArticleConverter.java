package com.MIBlog.converter;

import com.MIBlog.VO.ArticleVO;
import com.MIBlog.dao.TypeMapper;
import com.MIBlog.dao.UserMapper;
import com.MIBlog.dataobject.Article;
import com.MIBlog.entity.PageArticleVO;
import com.MIBlog.tunnel.ThumbUpTunnel;
import com.MIBlog.tunnel.UserTunnel;
import com.github.pagehelper.PageInfo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleConverter {

    private UserMapper userMapper;

    private TypeMapper typeMapper;

    private ThumbUpTunnel thumbUpTunnel;

    @Autowired
    public ArticleConverter(UserMapper userMapper, TypeMapper typeMapper, ThumbUpTunnel thumbUpTunnel) {
        this.userMapper = userMapper;
        this.typeMapper = typeMapper;
        this.thumbUpTunnel = thumbUpTunnel;
    }

    public PageArticleVO converter(List<Article> articles, PageInfo<Article> page){
        PageArticleVO pageArticleVO = new PageArticleVO();
        List<ArticleVO>articleVOS=articles.stream().map(x->{
            ArticleVO articleVO = new ArticleVO();
            articleVO.setId(x.getId());
            articleVO.setTitle(x.getTitle());
            articleVO.setImgurl(x.getImgurl());
            articleVO.setView(x.getView());
            articleVO.setTop(x.getTop());
            articleVO.setUserId(x.getUserId());
            articleVO.setTypeId(x.getTypeId());
            articleVO.setStatus(x.getStatus());
            if (x.getStatus()==1){
                articleVO.setStatusName("草稿");
            }else {
                articleVO.setStatusName("已发布");
            }
            articleVO.setGmtCreate(x.getGmtCreate());
            articleVO.setGmtModify(x.getGmtModify());
            articleVO.setSummary(x.getSummary());
            articleVO.setContent(x.getContent());
            articleVO.setLike(thumbUpTunnel.count(x.getId()));
            articleVO.setUser(userMapper.selectByPrimaryKey(x.getUserId()));
            articleVO.setType(typeMapper.selectByPrimaryKey(x.getTypeId()));
            //
            return articleVO;
        }).collect(Collectors.toList());
        pageArticleVO.setList(articleVOS);
        pageArticleVO.setTotal(page.getTotal());
        pageArticleVO.setPages(page.getPages());
        pageArticleVO.setIsFirstPage(page.isIsFirstPage());
        pageArticleVO.setIsLastPage(page.isIsLastPage());
        pageArticleVO.setIsHasPreviousPage(page.isHasPreviousPage());
        pageArticleVO.setIsHasNextPage(page.isHasNextPage());
        pageArticleVO.setPageNum(page.getPageNum());
        return pageArticleVO;
    }

    public List<ArticleVO> converter(List<Article> articles){
        PageArticleVO pageArticleVO = new PageArticleVO();
        List<ArticleVO>articleVOS=articles.stream().map(x->{
            ArticleVO articleVO = new ArticleVO();
            articleVO.setId(x.getId());
            articleVO.setTitle(x.getTitle());
            articleVO.setImgurl(x.getImgurl());
            articleVO.setView(x.getView());
            articleVO.setTop(x.getTop());
            articleVO.setUserId(x.getUserId());
            articleVO.setTypeId(x.getTypeId());
            articleVO.setStatus(x.getStatus());
            if (x.getStatus()==1){
                articleVO.setStatusName("草稿");
            }else {
                articleVO.setStatusName("已发布");
            }
            articleVO.setGmtCreate(x.getGmtCreate());
            articleVO.setGmtModify(x.getGmtModify());
            articleVO.setSummary(x.getSummary());
            articleVO.setContent(x.getContent());
            return articleVO;
        }).collect(Collectors.toList());
        return articleVOS;
    }
}
