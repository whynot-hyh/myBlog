package com.MIBlog.service.Impl;

import com.MIBlog.VO.UserVO;
import com.MIBlog.converter.UserConverter;
import com.MIBlog.dao.ArticleMapper;
import com.MIBlog.dao.UserMapper;
import com.MIBlog.dataobject.User;
import com.MIBlog.entity.PageEntity;
import com.MIBlog.enums.ErrorCode;
import com.MIBlog.exceptions.DBException;
import com.MIBlog.exmple.ArticleExample;
import com.MIBlog.exmple.UserExample;
import com.MIBlog.service.ArticleService;
import com.MIBlog.service.UserService;
import com.MIBlog.utils.RandomUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    private UserConverter userConverter;

    private ArticleMapper articleMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserConverter userConverter, ArticleMapper articleMapper) {
        this.userMapper = userMapper;
        this.userConverter = userConverter;
        this.articleMapper = articleMapper;
    }

    @Override
    public int insert(User user) {
        user.setIsadmin(0);
        user.setGmtCreate(new Date());
        user.setUserId(RandomUtils.generateUUID());
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andUserIdEqualTo(user.getUserId());
        if (userMapper.updateByExampleSelective(user,example)<=0){
            throw new DBException(ErrorCode.UPDATE_FAILED);
        }
        return 1;
    }

    @Override
    public int delete(String userId) {
        if (userMapper.deleteByPrimaryKey(userId)<=0){
            throw new DBException(ErrorCode.DELETE_FAILED);
        }
        ArticleExample example = new ArticleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        if (articleMapper.deleteByExample(example)<=0){
            throw new DBException(ErrorCode.DELETE_FAILED);
        }
        return 1;
    }

    @Override
    public UserVO getByUserId(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        UserVO userVO = new UserVO();
//        BeanUtils.copyProperties(user,userVO);
        userVO.setUserName(user.getUserName());
        userVO.setIsadmin(user.getIsadmin());
        userVO.setAvatar(user.getAvatar());
        userVO.setPassword(user.getPassword());
        userVO.setUserId(user.getUserId());
        userVO.setGmtCreate(user.getGmtCreate());
        if (userVO.getIsadmin()==0){
            userVO.setIsAdminName("普通用户");
        }else {
            userVO.setIsAdminName("管理员用户");
        }
        return  userVO;
    }

    @Override
    public User getUserDetail(String userId) {

       return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public PageInfo<UserVO> getUserList(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());
        UserExample example = new UserExample();
        example.setOrderByClause("gmt_create desc");
        example.createCriteria();
        return new PageInfo<>(userConverter.converter(userMapper.selectByExample(example)));
    }

    @Override
    public PageInfo<UserVO> search(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());
        UserExample example = new UserExample();
        example.setOrderByClause("gmt_create desc");
        example.createCriteria().andNicknameLike("%"+pageEntity.getSearch()+"%");

        return new PageInfo<>(userConverter.converter(userMapper.selectByExample(example)));
    }

    @Override
    public List<User> getUser(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserNameEqualTo(user.getUserName())
                .andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }
}
