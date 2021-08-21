package com.MIBlog.controller;

import com.MIBlog.VO.MenusVO;
import com.MIBlog.entity.ResponseWrapper;
import com.MIBlog.enums.ErrorCode;
import com.MIBlog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenusController{

    private MenuService menuService;

    @Autowired
    public MenusController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/list")
    public ResponseWrapper<List<MenusVO>> getMenus(Integer type){
        if (type==null){
            ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(menuService.getMenus(type));
    }
}
