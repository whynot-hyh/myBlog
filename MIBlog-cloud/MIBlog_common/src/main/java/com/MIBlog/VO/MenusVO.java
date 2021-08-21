package com.MIBlog.VO;

import com.MIBlog.dataobject.Menus;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MenusVO {

    private Integer id;


    private Integer parentId;


    private String menuName;

    private String url;

    private String icon;

    private Integer admin;

    private Date gmtCreate;

    private List<MenusVO> children=new ArrayList<>();
}
