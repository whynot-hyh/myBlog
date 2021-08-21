package com.MIBlog.tunnel;

import com.MIBlog.dataobject.Article;

import java.util.List;

public interface ArticleTunnel {

    List<Article> queryArticles();

    List<Article> queryTopList();

    List<Article> queryUserIds(List<String> userIds);
}
