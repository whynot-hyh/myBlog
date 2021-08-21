package com.MIBlog.tunnel.Impl;

import com.MIBlog.dao.ArticleMapper;
import com.MIBlog.dataobject.Article;
import com.MIBlog.exmple.ArticleExample;
import com.MIBlog.tunnel.ArticleTunnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ArticleTunnelImpl implements ArticleTunnel {

    private ArticleMapper articleMapper;

    @Autowired
    public ArticleTunnelImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public List<Article> queryArticles() {
        ArticleExample example = new ArticleExample();
        example.createCriteria();
        return articleMapper.selectByExample(example);
    }

    @Override
    public List<Article> queryTopList() {
        ArticleExample example = new ArticleExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andTopEqualTo(1);
        return articleMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<Article> queryUserIds(List<String> userIds) {
        ArticleExample example = new ArticleExample();
        example.createCriteria().andUserIdIn(userIds);
        return articleMapper.selectByExampleWithBLOBs(example);
    }
}
