package com.MIBlog.tunnel;

import com.MIBlog.dataobject.User;

import java.util.List;

public interface UserTunnel {


    List<User> queryUserIds(List<String> userIds);
}
