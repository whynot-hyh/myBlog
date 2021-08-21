package com.MIBlog.VO;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {

    private String userId;

    private String userName;

    private String password;

    private String nickname;

    private String avatar;

    private Integer isadmin;

    private String isAdminName;
    private Date gmtCreate;

}
