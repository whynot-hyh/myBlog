package com.MIBlog.controller;

import com.MIBlog.VO.UserVO;
import com.MIBlog.dataobject.User;
import com.MIBlog.entity.PageEntity;
import com.MIBlog.entity.ResponseWrapper;
import com.MIBlog.enums.ErrorCode;
import com.MIBlog.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/detail")
    public ResponseWrapper<UserVO> detail(String userId){
        if (StringUtils.isEmpty(userId)){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success( userService.getByUserId(userId));
    }

    @PostMapping("/list")
    public ResponseWrapper<PageInfo<UserVO>> list(@RequestBody PageEntity pageEntity){
        if (pageEntity.getIndex()<=0|| pageEntity.getSize()<=0){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(userService.getUserList(pageEntity));
    }

    @PostMapping("/update")
    public ResponseWrapper<Integer> update(@RequestBody User user){
        if (StringUtils.isEmpty(user.getUserId()) || StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getNickname())) {
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(userService.update(user));
    }

    @PostMapping("/search")
    public ResponseWrapper<PageInfo<UserVO>> search(@RequestBody PageEntity pageEntity){
        if (pageEntity.getIndex()<=0|| pageEntity.getSize()<=0){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(userService.search(pageEntity));
    }
    @GetMapping("/delete")
    public ResponseWrapper<Integer> delete(String userId){
        if (StringUtils.isEmpty(userId)){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(userService.delete(userId));
    }
}
