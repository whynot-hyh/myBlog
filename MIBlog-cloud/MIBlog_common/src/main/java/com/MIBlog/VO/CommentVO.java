package com.MIBlog.VO;

import com.MIBlog.dataobject.Article;
import com.MIBlog.dataobject.Comment;
import com.MIBlog.dataobject.User;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class CommentVO {

    private Integer id;

    private Integer pid;

    private Integer articleId;

    private String userId;

    private String receiver;

    private Date gmtCreate;

    private String description;

    private List<CommentVO> children;

    private User user;

    private Article article;
}
