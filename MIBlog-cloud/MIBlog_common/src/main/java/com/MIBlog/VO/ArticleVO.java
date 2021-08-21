package com.MIBlog.VO;

import com.MIBlog.dataobject.Type;
import com.MIBlog.dataobject.User;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleVO {

    private Integer id;

    private String title;

    private String imgurl;

    private Integer view;

    private Integer top;

    private String userId;

    private Integer typeId;

    private Integer status;

    private String statusName;

    private Date gmtCreate;

    private Date gmtModify;

    private String summary;

    private String content;

    private Integer like;

    private User user;
    private Type type;
}
