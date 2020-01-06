package com.volleyball.dao;

import java.util.List;

import com.volleyball.bean.Estimate;

public interface EstimateDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Estimate record);

    int insertSelective(Estimate record);

    Estimate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Estimate record);

    int updateByPrimaryKeyWithBLOBs(Estimate record);

    int updateByPrimaryKey(Estimate record);
    
    //手写
    List<Estimate> selectByGoodsId(String goodsId);
}