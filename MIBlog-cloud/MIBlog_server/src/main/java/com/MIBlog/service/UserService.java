package com.MIBlog.service;

import com.MIBlog.VO.UserVO;
import com.MIBlog.dataobject.User;
import com.MIBlog.entity.PageEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;

import java.util.List;

public interface UserService {

    int insert(User user);

    int update(User user);

    int delete(String userId);

    UserVO getByUserId(String userId);

    User getUserDetail(String userId);

    PageInfo<UserVO> getUserList(PageEntity pageEntity);

    PageInfo<UserVO> search(PageEntity pageEntity);

    List<User> getUser(User user);
}
