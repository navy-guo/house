package com.navy.config;

import tk.mybatis.mapper.entity.Condition;

import java.io.Serializable;
import java.util.List;

/**
 * ghj - 2017/1/21
 */
public interface BaseService <T,ID extends Serializable> {

    Integer save(T t);

    Integer update(T t);

    Integer delete(ID id);

    Integer delete(T t);

    T findOne(ID id);

    boolean exists(ID id);

    List<T> findAll(T t);

    List<T> findPage(T t, Integer page, Integer rows);

    List<T> findByCondition(Condition condition);

    List<T> findByConditionAndPage(Condition condition, Integer page, Integer rows);

}
