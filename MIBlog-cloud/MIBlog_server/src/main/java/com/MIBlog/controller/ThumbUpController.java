package com.MIBlog.controller;

import cn.hutool.core.util.StrUtil;
import com.MIBlog.dataobject.Thumbup;
import com.MIBlog.entity.ResponseWrapper;
import com.MIBlog.enums.ErrorCode;
import com.MIBlog.service.ThumbUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/thumb")
public class ThumbUpController {

    private ThumbUpService thumbUpService;

    @Autowired
    public ThumbUpController(ThumbUpService thumbUpService) {
        this.thumbUpService = thumbUpService;
    }

    @PostMapping("/like")
    public ResponseWrapper<Integer> like(@RequestBody Thumbup thumbup){
        if (StringUtils.isEmpty(thumbup.getUserId()) || thumbup.getArticleId()==null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
       return ResponseWrapper.success(thumbUpService.insert(thumbup));
    }


    @PostMapping("/unlike")
    public ResponseWrapper<Integer> unlike(@RequestBody Thumbup thumbup){
        if (StringUtils.isEmpty(thumbup.getUserId()) || thumbup.getArticleId()==null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(thumbUpService.delete(thumbup));
    }

    @GetMapping("/count")
    public ResponseWrapper<Integer> getThumbUpCount(Integer articleId){
        if (articleId==null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(thumbUpService.count(articleId));
    }

    /**
     * 我是否点赞
     */
    @GetMapping("/getIsLike")
    public ResponseWrapper<Integer> getIsLike(Integer articleId,String userId){
        if (articleId==null || StringUtils.isEmpty(userId)){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(thumbUpService.isLike(articleId,userId));
    }

}
