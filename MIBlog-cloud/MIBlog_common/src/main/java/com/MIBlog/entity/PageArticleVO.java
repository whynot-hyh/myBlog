package com.MIBlog.entity;

import com.MIBlog.VO.ArticleVO;
import lombok.Data;

import java.util.List;
@Data
public class PageArticleVO {
    List<ArticleVO> list;
    long total;
    long pages;
    long pageNum;
    long pageSize;
    Boolean isFirstPage;
    Boolean isLastPage;
    Boolean isHasPreviousPage;
    Boolean isHasNextPage;
}
