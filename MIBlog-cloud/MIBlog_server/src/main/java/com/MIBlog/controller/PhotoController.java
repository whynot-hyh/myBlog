package com.MIBlog.controller;

import com.MIBlog.dataobject.Photo;
import com.MIBlog.entity.PageEntity;
import com.MIBlog.entity.ResponseWrapper;
import com.MIBlog.enums.ErrorCode;
import com.MIBlog.service.PhotoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    private PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService){
        this.photoService = photoService;
    }


    @PostMapping("/insert")
    public ResponseWrapper<Integer> insert(@RequestBody Photo photo){
        if (StringUtils.isEmpty(photo.getImgurl()) || StringUtils.isEmpty(photo.getImgurl())){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(photoService.insert(photo));
    }


    @PostMapping("/getMyself")
    public ResponseWrapper<PageInfo<Photo>> getMyselfPhoto(@RequestBody PageEntity pageEntity){
        if (StringUtils.isEmpty(pageEntity.getUserId())){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        if (pageEntity.getIndex()<=0 || pageEntity.getSize()<=0){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success( photoService.getMyselfPhoto(pageEntity));
    }

    @PostMapping("/getPhotoList")
    public ResponseWrapper<PageInfo<Photo>> getPhotoList(@RequestBody PageEntity pageEntity){
        if (pageEntity.getIndex()<=0 || pageEntity.getSize()<=0){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success( photoService.getPhotoList(pageEntity));
    }

    @GetMapping("/delete")
    public ResponseWrapper<Integer> delete(String imgUrl){
        if (StringUtils.isEmpty(imgUrl)){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        photoService.delete(imgUrl);
        return ResponseWrapper.success();
    }
}
