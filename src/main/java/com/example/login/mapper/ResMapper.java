package com.example.login.mapper;

import com.example.login.bean.Res;
import org.apache.ibatis.annotations.Mapper;

public interface ResMapper {
    int deleteByPrimaryKey(Integer rId);

    int insert(Res record);

    int insertSelective(Res record);

    Res selectByPrimaryKey(Integer rId);

    Res selectByMD5(String rMD5);

    int updateByPrimaryKeySelective(Res record);

    int updateByPrimaryKey(Res record);
}