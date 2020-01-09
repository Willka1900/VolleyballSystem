package com.volleyball.dao;

import java.util.List;

import com.volleyball.bean.Goods;

public interface GoodsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
    
    //手写
    List<Goods> selectByKind(Integer kind);
}