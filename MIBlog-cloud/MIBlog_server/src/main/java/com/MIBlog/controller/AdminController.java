package com.MIBlog.controller;

import com.MIBlog.VO.UserVO;
import com.MIBlog.config.jwt.JWTUtils;
import com.MIBlog.dataobject.User;
import com.MIBlog.entity.ResponseWrapper;
import com.MIBlog.enums.ErrorCode;
import com.MIBlog.service.UserService;
import com.MIBlog.utils.Maps;
import com.MIBlog.utils.Result;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        List<User> users = userService.getUser(user);
        User user1 = users.get(0);
        if (user1!=null){
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user1,userVO);
            String token = JWTUtils.sign(user1);
            return Result.ok(Maps.build().put("token",token).put("user",userVO).getMap());
        }else {
            return Result.fail("用户名或密码错误");
        }
    }

    @PostMapping("/register")
    public ResponseWrapper<String> register(@RequestBody User user){
        if (StringUtils.isEmpty(user.getUserName())|| StringUtils.isEmpty(user.getPassword())){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        userService.insert(user);
        return ResponseWrapper.success();
    }



    /**
     * 验证token是否过期
     */
    @GetMapping("/isToken")
    public Result isToken(String token){
        if (token.equals("null")){
            return Result.ok(Boolean.FALSE);
        }else {
            boolean verify = JWTUtils.verify(token);
            return Result.ok(verify);
        }
    }

}
