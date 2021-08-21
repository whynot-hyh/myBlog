package com.MIBlog.service;

import com.MIBlog.VO.MenusVO;
import com.MIBlog.dataobject.Menus;
import org.springframework.util.StringUtils;

import java.util.List;

public interface MenuService {
    List<MenusVO> getMenus(Integer type);
}
