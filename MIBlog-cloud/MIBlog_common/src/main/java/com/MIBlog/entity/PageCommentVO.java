package com.MIBlog.entity;

import com.MIBlog.VO.CommentVO;
import lombok.Data;

import java.util.List;

@Data
public class PageCommentVO {
    List<CommentVO> list;
    long total;
    long pages;
    long pageNum;
    long pageSize;
    Boolean isFirstPage;
    Boolean isLastPage;
    Boolean isHasPreviousPage;
    Boolean isHasNextPage;

}
