package com.volleyball.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.volleyball.bean.NewGoods;

public interface NewGoodsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(NewGoods record);

    int uploadNewGoods(NewGoods record);

    NewGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewGoods record);

    int updateByPrimaryKey(NewGoods record);
    
    //手写
    List<NewGoods> selectByKind(@Param("kind")Integer kind);
}