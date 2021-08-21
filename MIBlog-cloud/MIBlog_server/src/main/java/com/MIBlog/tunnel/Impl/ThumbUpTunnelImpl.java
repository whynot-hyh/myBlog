package com.MIBlog.tunnel.Impl;

import com.MIBlog.dao.ThumbupMapper;
import com.MIBlog.dataobject.Thumbup;
import com.MIBlog.enums.ErrorCode;
import com.MIBlog.exceptions.DBException;
import com.MIBlog.exmple.ThumbupExample;
import com.MIBlog.tunnel.ThumbUpTunnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ThumbUpTunnelImpl implements ThumbUpTunnel {

    private ThumbupMapper thumbupMapper;

    @Autowired
    public ThumbUpTunnelImpl(ThumbupMapper thumbupMapper) {
        this.thumbupMapper = thumbupMapper;
    }


    @Override
    public List<Thumbup> queryByArticleIdAndUserId(Integer articleId, String userId) {
        ThumbupExample example = new ThumbupExample();
        example.createCriteria()
                .andArticleIdEqualTo(articleId)
                .andUserIdEqualTo(userId);
        return thumbupMapper.selectByExample(example);
    }

    public  int insert(Thumbup thumbup){
        if (thumbupMapper.insert(thumbup)<=0){
            throw new DBException(ErrorCode.CREATE_FAILED);
        }
        return 1;
    }

    @Override
    public int delete(Thumbup thumbup) {
        ThumbupExample example = new ThumbupExample();
        example.createCriteria()
                .andArticleIdEqualTo(thumbup.getArticleId())
                .andUserIdEqualTo(thumbup.getUserId());

        if (thumbupMapper.deleteByExample(example)<=0){
            throw new DBException(ErrorCode.DELETE_FAILED);
        }
        return 1;
    }

    @Override
    public Integer count(Integer articleId) {
        ThumbupExample example = new ThumbupExample();
        example.createCriteria()
                .andArticleIdEqualTo(articleId);
        return thumbupMapper.selectByExample(example).size();
    }

}
