package com.MIBlog.service.Impl;

import com.MIBlog.VO.MenusVO;
import com.MIBlog.converter.MenusConverter;
import com.MIBlog.dao.MenusMapper;
import com.MIBlog.dataobject.Menus;
import com.MIBlog.exmple.MenusExample;
import com.MIBlog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("all")
public class MenusServiceImpl implements MenuService {
    @Autowired
    private MenusMapper menusMapper;
    @Autowired
    MenusConverter menusConverter;

    @Override
    public List<MenusVO> getMenus(Integer type) {
        List<MenusVO> menus=new ArrayList<>();
        if (type==0){
            //普通用户
            MenusExample example = new MenusExample();
            example.createCriteria()
                    .andAdminEqualTo(0)
                    .andParentIdEqualTo(0)
                    .andAdminEqualTo(0);
            List<Menus> menus1 = menusMapper.selectByExample(example);
           menus= menusConverter.converter(menus1).stream().map(x->{
               MenusExample example1 = new MenusExample();
               example1.createCriteria().andParentIdEqualTo(x.getId()).andAdminEqualTo(0);
               x.setChildren(menusConverter.converter(menusMapper.selectByExample(example1)));
               return x;
           }).collect(Collectors.toList());;
        }else if (type==1){
            MenusExample example = new MenusExample();
            example.createCriteria()
                    .andParentIdEqualTo(0);
            List<Menus> menus1 = menusMapper.selectByExample(example);
            menus= menusConverter.converter(menus1).stream().map(x->{
                MenusExample example1 = new MenusExample();
                example1.createCriteria().andParentIdEqualTo(x.getId());
                x.setChildren(menusConverter.converter(menusMapper.selectByExample(example1)));
                return x;
            }).collect(Collectors.toList());;
        }
        return menus;
    }
}
