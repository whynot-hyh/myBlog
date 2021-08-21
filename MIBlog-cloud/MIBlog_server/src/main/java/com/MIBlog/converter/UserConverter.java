package com.MIBlog.converter;

import com.MIBlog.VO.UserVO;
import com.MIBlog.dataobject.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public List<UserVO> converter(List<User> users){
       return users.stream().map(x->{
            UserVO userVO = new UserVO();
            userVO.setUserId(x.getUserId());
            userVO.setUserName(x.getUserName());
            userVO.setNickname(x.getNickname());
            userVO.setIsadmin(x.getIsadmin());
            userVO.setAvatar(x.getAvatar());
            if (x.getIsadmin()==0){
                userVO.setIsAdminName("普通用户");
            }else {
                userVO.setIsAdminName("管理员用户");
            }
            userVO.setGmtCreate(x.getGmtCreate());
            return userVO;
        }).collect(Collectors.toList());
    }

}
