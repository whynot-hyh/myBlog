package com.MIBlog.tunnel;

import com.MIBlog.dataobject.Thumbup;

import javax.swing.*;
import java.util.List;

public interface ThumbUpTunnel {

    List<Thumbup> queryByArticleIdAndUserId(Integer articleId, String userId);
    int insert(Thumbup thumbup);

    int delete(Thumbup thumbup);

    Integer count(Integer articleId);
}
