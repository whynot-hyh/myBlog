package com.MIBlog.service;

import com.MIBlog.dataobject.Type;
import com.MIBlog.entity.PageEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TypeService {

    int insert(Type type);

    int update(Type type);

    List<Type> getAll();

    PageInfo<Type> getTypeList(Integer index,Integer size);

    PageInfo<Type> getSearch(PageEntity pageEntity);

    int delete(Integer id);
}
