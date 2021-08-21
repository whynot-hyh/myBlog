package com.MIBlog.service;

import com.MIBlog.dataobject.Thumbup;

import java.util.List;

public interface ThumbUpService {

    int insert(Thumbup thumbup);

    int delete(Thumbup thumbup);


    Integer count(Integer articleId);

    Integer isLike(Integer articleId,String userId);
}
