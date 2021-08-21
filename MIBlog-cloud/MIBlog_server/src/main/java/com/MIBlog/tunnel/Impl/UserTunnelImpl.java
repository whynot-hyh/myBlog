package com.MIBlog.tunnel.Impl;

import com.MIBlog.dao.UserMapper;
import com.MIBlog.dataobject.User;
import com.MIBlog.exmple.UserExample;
import com.MIBlog.tunnel.UserTunnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserTunnelImpl implements UserTunnel {

    private UserMapper userMapper;
    @Autowired
    public UserTunnelImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> queryUserIds(List<String> userIds) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdIn(userIds);
        return  userMapper.selectByExample(userExample);
    }
}
