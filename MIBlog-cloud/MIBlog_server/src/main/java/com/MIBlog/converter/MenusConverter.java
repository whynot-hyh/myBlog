package com.MIBlog.converter;

import com.MIBlog.VO.MenusVO;
import com.MIBlog.dataobject.Menus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenusConverter {

    public List<MenusVO> converter(List<Menus> menus){
        List<MenusVO> menusVOS = new ArrayList<>();
        menus.stream().map(x->{
            MenusVO menusVO = new MenusVO();
            menusVO.setId(x.getId());
            menusVO.setParentId(x.getParentId());
            menusVO.setMenuName(x.getMenuName());
            menusVO.setUrl(x.getUrl());
            menusVO.setIcon(x.getIcon());
            menusVO.setAdmin(x.getAdmin());
            menusVO.setGmtCreate(x.getGmtCreate());
            menusVOS.add(menusVO);
            return x;
        }).collect(Collectors.toList());
        return menusVOS;
    }
}
