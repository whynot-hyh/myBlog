package com.MIBlog.tunnel;

import com.MIBlog.dataobject.Comment;
import org.springframework.util.StringUtils;

import java.util.List;

public interface CommentTunnel {


    List<Comment> queryByPidList(List<Integer> commentIds);

    List<Comment> queryCommentList();

    List<Comment> search(String search);

    List<Comment> searchAndUser(String search,String userId);
}
