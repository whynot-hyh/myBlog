package com.MIBlog.tunnel.Impl;

import com.MIBlog.dao.CommentMapper;
import com.MIBlog.dataobject.Comment;
import com.MIBlog.exmple.CommentExample;
import com.MIBlog.tunnel.CommentTunnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentTunnelImpl implements CommentTunnel {

    private CommentMapper commentMapper;

    @Autowired
    public CommentTunnelImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> queryByPidList(List<Integer> commentIds) {
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andPidIn(commentIds);
        return commentMapper.selectByExample(example);
    }

    @Override
    public List<Comment> queryCommentList() {
        CommentExample example = new CommentExample();
        example.createCriteria();
        return commentMapper.selectByExample(example);
    }

    @Override
    public List<Comment> search(String search) {
        CommentExample example = new CommentExample();
        example.createCriteria().andDescriptionLike("%"+search+"%");
        return commentMapper.selectByExample(example);
    }

    @Override
    public List<Comment> searchAndUser(String search, String userId) {
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andDescriptionLike("%"+search+"%");
        return commentMapper.selectByExample(example);
    }
}
