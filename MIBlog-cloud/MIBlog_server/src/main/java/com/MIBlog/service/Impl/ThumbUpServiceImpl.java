package com.MIBlog.service.Impl;

import com.MIBlog.dataobject.Thumbup;
import com.MIBlog.service.ThumbUpService;
import com.MIBlog.tunnel.ThumbUpTunnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThumbUpServiceImpl implements ThumbUpService {

    private ThumbUpTunnel thumbUpTunnel;

    @Autowired
    public ThumbUpServiceImpl(ThumbUpTunnel thumbUpTunnel) {
        this.thumbUpTunnel = thumbUpTunnel;
    }

    @Override
    public int insert(Thumbup thumbup) {
        return thumbUpTunnel.insert(thumbup);
    }

    @Override
    public int delete(Thumbup thumbup) {
        return thumbUpTunnel.delete(thumbup);
    }

    @Override
    public Integer count(Integer articleId) {
        return thumbUpTunnel.count(articleId);
    }

    @Override
    public Integer isLike(Integer articleId, String userId) {
        List<Thumbup> thumbups = thumbUpTunnel.queryByArticleIdAndUserId(articleId, userId);
        if (thumbups.size()!=0){
            return 1;
        }else {
            return 0;
        }
    }
}
