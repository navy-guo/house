package com.navy.mapper;

import com.navy.config.MyMapper;
import com.navy.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends MyMapper<User,Integer> {
}