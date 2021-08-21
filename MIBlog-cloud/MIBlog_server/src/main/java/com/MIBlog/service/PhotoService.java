package com.MIBlog.service;

import com.MIBlog.dataobject.Photo;
import com.MIBlog.entity.PageEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;

import java.util.List;

public interface PhotoService {

    int insert(Photo photo);

    PageInfo<Photo> getMyselfPhoto(PageEntity pageEntity);

    PageInfo<Photo> getPhotoList(PageEntity pageEntity);


    void   delete(String imgUrl);
}
