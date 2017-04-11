package com.navy.config;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.io.Serializable;
import java.util.List;

/**
 * ghj - 2017/1/21
 */
public class BaseServiceImpl<T,ID extends Serializable> implements BaseService<T,ID> {

    protected MyMapper<T,ID> myMapper;

    public MyMapper<T, ID> getBaseMapper() {
        return myMapper;
    }
    @Autowired
    public void setMyMapper(MyMapper<T, ID> myMapper) {
        this.myMapper = myMapper;
    }

    @Override
    public Integer save(T t) {
        return getBaseMapper().insert(t);
    }

    @Override
    public Integer update(T t) {
        return getBaseMapper().updateByPrimaryKeySelective(t);
    }

    @Override
    public Integer delete(ID id) {
        return getBaseMapper().deleteByPrimaryKey(id);
    }

    @Override
    public Integer delete(T t) {
        return getBaseMapper().delete(t);
    }

    @Override
    public T findOne(ID id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }

    @Override
    public boolean exists(ID id) {
        boolean flag=false;
        if (getBaseMapper().selectByPrimaryKey(id)!=null)
            flag=true;
        return flag;
    }


    @Override
    public List<T> findAll(T t) {
        return getBaseMapper().select(t);
    }

    @Override
    public List<T> findPage(T t,Integer page,Integer rows) {
        if (page != null && page > 0 && rows != null && rows > 0) {
            PageHelper.startPage(page,rows);
        }
        return getBaseMapper().select(t);
    }

    @Override
    public List<T> findByCondition(Condition condition) {
        return getBaseMapper().selectByExample(condition);
    }


    public List<T> findByConditionAndPage(Condition condition,Integer page,Integer rows)
    {
        if(page!=null && page>0 && rows!=null && rows>0){
            PageHelper.startPage(page,rows);
        }
        return getBaseMapper().selectByExample(condition);
    }

}
