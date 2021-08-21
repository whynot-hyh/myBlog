package com.MIBlog.converter;

import com.MIBlog.VO.CommentVO;
import com.MIBlog.dataobject.Comment;
import com.MIBlog.entity.PageCommentVO;
import com.MIBlog.service.ArticleService;
import com.MIBlog.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentConverter {

    private UserService userService;

    private ArticleService  articleService;

    @Autowired
    public CommentConverter(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    public PageCommentVO converter(List<Comment> comments, PageInfo<Comment> page) {
        PageCommentVO pageCommentVO = new PageCommentVO();
        List<CommentVO> commentVOS = comments.stream().map(e -> {
            CommentVO commentVO = new CommentVO();
            commentVO.setId(e.getId());
            commentVO.setPid(e.getPid());
            commentVO.setArticleId(e.getArticleId());
            commentVO.setUserId(e.getUserId());
            commentVO.setReceiver(e.getReceiver());
            commentVO.setGmtCreate(e.getGmtCreate());
            commentVO.setDescription(e.getDescription());
            commentVO.setUser(userService.getUserDetail(e.getUserId()));
            commentVO.setArticle(articleService.getArticleById(e.getArticleId()));
            return commentVO;
        }).collect(Collectors.toList());
        pageCommentVO.setList(commentVOS);
        pageCommentVO.setTotal(page.getTotal());
        pageCommentVO.setPages(page.getPages());
        pageCommentVO.setIsFirstPage(page.isIsFirstPage());
        pageCommentVO.setIsLastPage(page.isIsLastPage());
        pageCommentVO.setIsHasPreviousPage(page.isHasPreviousPage());
        pageCommentVO.setIsHasNextPage(page.isHasNextPage());
        pageCommentVO.setPageNum(page.getPageNum());

        return pageCommentVO;
    }

    public  List<CommentVO> converter(List<Comment> comments) {
       return comments.stream().map(e->{
            CommentVO commentVO = new CommentVO();
            commentVO.setId(e.getId());
            commentVO.setPid(e.getPid());
            commentVO.setArticleId(e.getArticleId());
            commentVO.setUserId(e.getUserId());
            commentVO.setReceiver(e.getReceiver());
            commentVO.setGmtCreate(e.getGmtCreate());
            commentVO.setDescription(e.getDescription());
            commentVO.setUser(userService.getUserDetail(e.getUserId()));
            commentVO.setArticle(articleService.getArticleById(e.getArticleId()));
            commentVO.setGmtCreate(e.getGmtCreate());
            return commentVO;
        }).collect(Collectors.toList());
    }

}
