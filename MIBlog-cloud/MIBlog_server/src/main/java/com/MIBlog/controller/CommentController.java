package com.MIBlog.controller;

import com.MIBlog.VO.CommentVO;
import com.MIBlog.dataobject.Comment;
import com.MIBlog.entity.PageCommentVO;
import com.MIBlog.entity.PageEntity;
import com.MIBlog.entity.ResponseWrapper;
import com.MIBlog.enums.ErrorCode;
import com.MIBlog.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/insert")
    public ResponseWrapper<Integer> insert(@RequestBody Comment comment){
        if (StringUtils.isEmpty(comment.getArticleId()) || StringUtils.isEmpty(comment.getUserId())) {
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        System.out.println("comment==>"+comment);
        return ResponseWrapper.success(commentService.insert(comment));
    }

    @GetMapping("/getArticle")
    public ResponseWrapper<List<CommentVO>> getArticleComment(Integer articleId){
        if (articleId==null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(commentService.getArticleComment(articleId));
    }

    //我的评论
    @PostMapping("/getMySelfComments")
    public ResponseWrapper<PageCommentVO> getMySelfComments(@RequestBody PageEntity pageEntity){
        if (StringUtils.isEmpty(pageEntity)){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(commentService.getMyselfComment(pageEntity));
    }

    //评论我的
    @PostMapping("/getReceiverComments")
    public ResponseWrapper<PageCommentVO> getReceiverComments(@RequestBody PageEntity pageEntity){
        if (StringUtils.isEmpty(pageEntity)){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(commentService.getReceiverComment(pageEntity));
    }


    @PostMapping("/list")
    public ResponseWrapper<PageCommentVO> commentList(@RequestBody PageEntity pageEntity){
        if (pageEntity.getIndex()<=0 || pageEntity.getSize()<=0){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(commentService.getCommentList(pageEntity));
    }

    //查询
    @PostMapping("/search")
    public ResponseWrapper<PageCommentVO> search(@RequestBody PageEntity pageEntity){
        if (StringUtils.isEmpty(pageEntity.getSearch())){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        if (pageEntity.getIndex()<=0 || pageEntity.getSize()<=0){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(commentService.search(pageEntity));
    }

    @GetMapping("/delete")
    public ResponseWrapper<Integer> delete(Integer id){
        if (id==null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(commentService.delete(id));
    }


}
