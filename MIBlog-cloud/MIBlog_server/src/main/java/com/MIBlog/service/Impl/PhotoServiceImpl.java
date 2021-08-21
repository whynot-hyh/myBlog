package com.MIBlog.service.Impl;

import com.MIBlog.dao.PhotoMapper;
import com.MIBlog.dao.UserMapper;
import com.MIBlog.dataobject.Photo;
import com.MIBlog.entity.PageEntity;
import com.MIBlog.enums.ErrorCode;
import com.MIBlog.exceptions.DBException;
import com.MIBlog.exmple.PhotoExample;
import com.MIBlog.service.PhotoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    private PhotoMapper photoMapper;

    private UserMapper userMapper;

    @Autowired
    public PhotoServiceImpl(PhotoMapper photoMapper, UserMapper userMapper) {
        this.photoMapper = photoMapper;
        this.userMapper = userMapper;
    }

    @Override
    public int insert(Photo photo) {
        if (photoMapper.insert(photo)<=0){
            throw new DBException(ErrorCode.CREATE_FAILED);
        }
        return 1;
    }

    @Override
    public PageInfo<Photo> getMyselfPhoto(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());
        PhotoExample example = new PhotoExample();
        example.createCriteria().andUserIdEqualTo(pageEntity.getUserId());
        return new PageInfo<>(photoMapper.selectByExample(example));
    }

    @Override
    public PageInfo<Photo> getPhotoList(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());
        PhotoExample example = new PhotoExample();
        example.createCriteria();
        return new PageInfo<>(photoMapper.selectByExample(example));
    }

    @Override
    public void delete(String imgUrl) {
        PhotoExample example = new PhotoExample();
        example.createCriteria().andImgurlEqualTo(imgUrl);
        if (photoMapper.deleteByExample(example)<=0){
            throw new DBException(ErrorCode.CREATE_FAILED);
        }
    }
}
