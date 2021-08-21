package com.MIBlog.service.Impl;

import com.MIBlog.VO.CommentVO;
import com.MIBlog.converter.CommentConverter;
import com.MIBlog.dao.ArticleMapper;
import com.MIBlog.dao.CommentMapper;
import com.MIBlog.dataobject.Comment;
import com.MIBlog.entity.PageCommentVO;
import com.MIBlog.entity.PageEntity;
import com.MIBlog.enums.ErrorCode;
import com.MIBlog.exceptions.DBException;
import com.MIBlog.exmple.CommentExample;
import com.MIBlog.service.CommentService;
import com.MIBlog.tunnel.CommentTunnel;
import com.MIBlog.tunnel.UserTunnel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentMapper commentMapper;

    private ArticleMapper articleMapper;

    private CommentConverter commentConverter;

    private CommentTunnel commentTunnel;

    private UserTunnel userTunnel;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper, ArticleMapper articleMapper, CommentConverter commentConverter,CommentTunnel commentTunnel,UserTunnel userTunnel) {
        this.commentMapper = commentMapper;
        this.articleMapper = articleMapper;
        this.commentConverter = commentConverter;
        this.commentTunnel=commentTunnel;
        this.userTunnel=userTunnel;
    }

    @Override
    public int insert(Comment comment) {
        comment.setGmtCreate(new Date());
        if (commentMapper.insert(comment)<=0){
            throw new DBException(ErrorCode.CREATE_FAILED);
        }
        return 1;
    }

    @Override
    public Integer count() {
        //查询出所有评论的数量
        CommentExample example = new CommentExample();
        example.createCriteria();
        return commentMapper.selectByExample(example).size();
    }

    @Override
    public PageCommentVO getCommentList(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());
        CommentExample example = new CommentExample();
        example.setOrderByClause("id desc");
        example.createCriteria();
        List<Comment> comments = commentMapper.selectByExample(example);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        return commentConverter.converter(comments,pageInfo);
    }

    @Override
    public PageCommentVO getMyselfComment(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());
        CommentExample example = new CommentExample();
        example.setOrderByClause("id desc");
        example.createCriteria()
                .andUserIdEqualTo(pageEntity.getUserId());
        List<Comment> comments = commentMapper.selectByExample(example);
        System.out.println(comments);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        return commentConverter.converter(comments,pageInfo);
    }

    @Override
    public PageCommentVO getReceiverComment(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());
        CommentExample example = new CommentExample();
        example.setOrderByClause("id desc");
        example.createCriteria()
                .andReceiverEqualTo(pageEntity.getReceiver());
        List<Comment> comments = commentMapper.selectByExample(example);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        return commentConverter.converter(comments,pageInfo);
    }

    @Override
    public PageCommentVO search(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());
        if (!StringUtils.isEmpty(pageEntity.getUserId())){
            List<Comment> comments = commentTunnel.searchAndUser(pageEntity.getSearch(), pageEntity.getUserId());
            PageInfo<Comment> pageInfo = new PageInfo<>(comments);
            return commentConverter.converter(comments,pageInfo);
        }
        List<Comment> search = commentTunnel.search(pageEntity.getSearch());
        PageInfo<Comment> pageInfo = new PageInfo<>(search);
        return commentConverter.converter(search,pageInfo);
    }

    @Override
    public int delete(Integer id) {
        if (  commentMapper.deleteByPrimaryKey(id)<=0){
            throw new DBException(ErrorCode.DELETE_FAILED);
        }
        return 1;
    }

    @Override
    public List<Comment> queryByPidList(List<Integer> commentIds) {
       return   Lists.partition(commentIds,20).stream()
         .flatMap(x-> commentTunnel.queryByPidList(x).stream())
         .collect(Collectors.toList());
    }

    @Override
    public List<CommentVO> getArticleComment(Integer articleId) {
        CommentExample example = new CommentExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andArticleIdEqualTo(articleId)
                    .andPidEqualTo(0);
        List<Comment> comments = commentMapper.selectByExample(example);
        List<CommentVO> converter = commentConverter.converter(comments);

        List<Integer>commentIds=new ArrayList<>();
        converter.stream().map(x->{
            commentIds.add(x.getId());
            return x;
        }).collect(Collectors.toList());

        List<CommentVO> comments1 = commentConverter.converter(queryByPidList(commentIds));
        return converter.stream().map(x -> {
//            comments1.stream().allMatch(a -> x.getId() == a.getPid() ? children.add(a) : children.add(null));
            List<CommentVO> children = comments1.stream()
                    .filter(a -> a.getPid() == x.getId())
                    .collect(Collectors.toList());
            x.setChildren(children);
            return x;
        }).collect(Collectors.toList());
    }
}
