package com.MIBlog.tunnel.Impl;

import com.MIBlog.dao.PhotoMapper;
import com.MIBlog.dataobject.Photo;
import com.MIBlog.exmple.PhotoExample;
import com.MIBlog.tunnel.PhotoTunnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhotoTunnelImpl implements PhotoTunnel {

    private PhotoMapper photoMapper;

    @Autowired
    public PhotoTunnelImpl(PhotoMapper photoMapper) {
        this.photoMapper = photoMapper;
    }

    @Override
    public List<Photo> queryList() {
        PhotoExample example = new PhotoExample();
        example.createCriteria();
        return photoMapper.selectByExample(example);
    }
}
