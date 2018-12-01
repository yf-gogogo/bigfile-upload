package com.example.login.mapper;

import com.example.login.bean.ResMark;
import org.apache.ibatis.annotations.Mapper;


public interface ResMarkMapper {
    int deleteByPrimaryKey(Integer markId);

    int insert(ResMark record);

    int insertSelective(ResMark record);

    ResMark selectByPrimaryKey(Integer markId);

    int updateByPrimaryKeySelective(ResMark record);

    int updateByPrimaryKey(ResMark record);
}