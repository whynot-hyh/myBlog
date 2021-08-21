package com.MIBlog.service;

import com.MIBlog.VO.CommentVO;
import com.MIBlog.dataobject.Comment;
import com.MIBlog.entity.PageCommentVO;
import com.MIBlog.entity.PageEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService {

    int insert(Comment comment);

    Integer count();

    PageCommentVO getCommentList(PageEntity pageEntity);

    PageCommentVO getMyselfComment(PageEntity pageEntity);

    PageCommentVO getReceiverComment(PageEntity pageEntity);

    PageCommentVO search(PageEntity pageEntity);

    int delete(Integer id);

    List<Comment> queryByPidList(List<Integer> commentIds);

    List<CommentVO> getArticleComment(Integer articleId);
}
