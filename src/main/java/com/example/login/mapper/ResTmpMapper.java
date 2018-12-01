package com.example.login.mapper;

import com.example.login.bean.ResTmp;
import org.apache.ibatis.annotations.Mapper;


public interface ResTmpMapper {
    int deleteByPrimaryKey(Integer tmpId);

    int deleteByMD5(String tmpTitle);

    int insert(ResTmp record);

    int insertSelective(ResTmp record);

    ResTmp selectByPrimaryKey(Integer tmpId);

    int updateByPrimaryKeySelective(ResTmp record);

    int updateByPrimaryKey(ResTmp record);
}