package com.volleyball.dao;


import com.volleyball.bean.test;

public interface testMapper {
    int deleteByPrimaryKey(String test);

    int insert(test record);

    int insertSelective(test record);

    test selectByPrimaryKey(String test);

    int updateByPrimaryKeySelective(test record);

    int updateByPrimaryKey(test record);
}