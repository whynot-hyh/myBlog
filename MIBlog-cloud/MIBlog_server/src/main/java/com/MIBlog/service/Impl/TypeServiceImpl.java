package com.MIBlog.service.Impl;

import com.MIBlog.dao.TypeMapper;
import com.MIBlog.dataobject.Type;
import com.MIBlog.entity.PageEntity;
import com.MIBlog.enums.ErrorCode;
import com.MIBlog.exceptions.DBException;
import com.MIBlog.exmple.TypeExample;
import com.MIBlog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private TypeMapper typeMapper;


    @Autowired
    public TypeServiceImpl(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @Override
    public int insert(Type type) {
        type.setGmtCreate(new Date());
        if (typeMapper.insert(type)<=0){
            throw new DBException(ErrorCode.CREATE_FAILED);
        }
        return 1;
    }

    @Override
    public int update(Type type) {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andIdEqualTo(type.getId());
        if (typeMapper.updateByExample(type,typeExample)<=0){
            throw new DBException(ErrorCode.UPDATE_FAILED);
        }
        return 1;
    }

    @Override
    public List<Type> getAll() {
        TypeExample ex = new TypeExample();
        ex.createCriteria();
        return typeMapper.selectByExample(ex);
    }

    @Override
    public PageInfo<Type> getTypeList(Integer index, Integer size) {
        PageHelper.startPage(index,size);
        TypeExample ex = new TypeExample();
        ex.createCriteria();
        return new PageInfo<>(typeMapper.selectByExample(ex));
    }

    @Override
    public PageInfo<Type> getSearch(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getIndex(),pageEntity.getSize());
        TypeExample example = new TypeExample();
        example.createCriteria().andTypeNameLike("%"+pageEntity.getSearch()+"%");
        return new PageInfo<>(typeMapper.selectByExample(example));
    }

    @Override
    public int delete(Integer id) {
        if (  typeMapper.deleteByPrimaryKey(id)<=0){
            throw new DBException(ErrorCode.UPDATE_FAILED);
        }
        return 1;
    }
}
