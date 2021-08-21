package com.MIBlog.controller;

import com.MIBlog.dataobject.Type;
import com.MIBlog.entity.PageEntity;
import com.MIBlog.entity.ResponseWrapper;
import com.MIBlog.enums.ErrorCode;
import com.MIBlog.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    private TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/all")
    public ResponseWrapper<List<Type>> all(){
        return  ResponseWrapper.success(typeService.getAll());
    }


    @PostMapping("/insert")
    public ResponseWrapper<Integer> insert(@RequestBody Type type){
        if (StringUtils.isEmpty(type.getTypeName())){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(typeService.insert(type));
    }

    @PostMapping("/update")
    public ResponseWrapper<Integer> update(@RequestBody Type type){
        if (type.getId()==null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        if (StringUtils.isEmpty(type.getTypeName())){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(typeService.update(type));
    }

    @PostMapping("/search")
    public ResponseWrapper<PageInfo<Type>> search(@RequestBody PageEntity pageEntity){
        if (StringUtils.isEmpty(pageEntity.getSearch())){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(typeService.getSearch(pageEntity));
    }

    @PostMapping("list")
    public ResponseWrapper<PageInfo<Type>> list(@RequestBody PageEntity pageEntity){
        if (pageEntity.getIndex()<=0 || pageEntity.getSize()<=0){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(typeService.getTypeList(pageEntity.getIndex(),pageEntity.getSize()));
    }

    @DeleteMapping("/delete")
    public ResponseWrapper<Integer> delete(Integer id){
        if (id==null){
            return ResponseWrapper.fail(ErrorCode.PARAM_INVALID_ERROR);
        }
        return ResponseWrapper.success(typeService.delete(id));
    }

}
