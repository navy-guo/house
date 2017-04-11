package com.navy.mapper;

import com.navy.config.MyMapper;
import com.navy.entity.House;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseMapper extends MyMapper<House,Integer> {

}