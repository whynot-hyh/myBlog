package com.MIBlog.controller;

import com.MIBlog.VO.ArticleVO;

import com.MIBlog.dataobject.Article;
import com.MIBlog.entity.PageArticleVO;
import com.MIBlog.entity.PageEntity;
import com.MIBlog.entity.ResponseWrapper;
import com.MIBlog.enums.ErrorCode;
import com.MIBlog.service.ArticleService;
import com.MIBlog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/article")
public class ArticleController {
    private ArticleService articleService;

    private CommentService commentService;

    @Autowired
    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @PostMapping("/insert")
    public ResponseWrapper<Integer> insert(@RequestBody Article article){
        if (StringUtils.isEmpty(article.getTitle())|| StringUtils.isEmpty(article.getContent())){
            return  ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        articleService.insert(article);
        return ResponseWrapper.success();
    }

    @PostMapping("/list")
    public ResponseWrapper<PageArticleVO>  list(@RequestBody PageEntity pageEntity){
        if (StringUtils.isEmpty(pageEntity)){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        if (pageEntity.getIndex()<=0 || pageEntity.getSize()<=0){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(articleService.getList(pageEntity));
    }


    @PostMapping("/userList")
    public ResponseWrapper<PageArticleVO>  userList(@RequestBody PageEntity pageEntity){
        if (StringUtils.isEmpty(pageEntity)){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        if (pageEntity.getIndex()<=0 || pageEntity.getSize()<=0){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(articleService.getArticleList(pageEntity));
    }

    @PostMapping("/search")
    public ResponseWrapper<PageArticleVO> search(@RequestBody PageEntity pageEntity){
        if (StringUtils.isEmpty(pageEntity.getSearch())){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(articleService.getSearch(pageEntity));
    }

    @PostMapping("/update")
    public ResponseWrapper<Integer> update(@RequestBody Article article){
        if (StringUtils.isEmpty(article.getTitle())|| StringUtils.isEmpty(article.getContent())){
            return  ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(articleService.update(article));
    }


    @PostMapping("/getTypeArticle")
    public ResponseWrapper<PageArticleVO>getTypeArticle(@RequestBody PageEntity pageEntity){
        if (pageEntity.getIndex()<=0 || pageEntity.getSize()<=0){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(articleService.getTypeArticle(pageEntity));
    }

    @GetMapping("/detail")
    public ResponseWrapper<ArticleVO> detail(Integer id){
        if (id==null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        ArticleVO detail = articleService.detail(id);
        return ResponseWrapper.success(detail);
    }

    @GetMapping("/cat")
    public ResponseWrapper<ArticleVO> cat(Integer id){
        if (id==null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        ArticleVO detail = articleService.cat(id);
        return ResponseWrapper.success(detail);
    }

    @GetMapping("/getArticleView")
    public ResponseWrapper<PageArticleVO> getArticleView(){
        return ResponseWrapper.success(articleService.getArticleView());
    }

    @GetMapping("/get10Article")
    public ResponseWrapper<PageArticleVO> get10Article(){
        return ResponseWrapper.success(articleService.get10Article());
    }

    @GetMapping("/getTops")
    public ResponseWrapper<List<ArticleVO>> getTops(){
        return ResponseWrapper.success(articleService.getTops());
    }

    @GetMapping("/count")
    public ResponseWrapper<Map<String,Object>> count(){
        Map<String, Object> map = new HashMap<>();
        map.put("articleCount",articleService.count());
        map.put("commentCount",commentService.count());
        return ResponseWrapper.success(map);
    }

    @GetMapping("/delete")
    public ResponseWrapper<Integer> delete(Integer id){
        articleService.delete(id);
        return ResponseWrapper.success();
    }

    @GetMapping("/getDataView")
    public ResponseWrapper<List<Integer>> getDataView(){
        return ResponseWrapper.success(articleService.getDataViews());
    }

}
