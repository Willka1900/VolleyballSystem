package com.volleyball.dao;

import java.util.List;

import com.volleyball.bean.NewVbPlace;
import com.volleyball.bean.PCA;

public interface NewVbPlaceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(NewVbPlace record);

    int uploadNewVbPlace(NewVbPlace record);

    NewVbPlace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewVbPlace record);

    int updateByPrimaryKey(NewVbPlace record);
    
    //手写
    List<NewVbPlace> selectByPCA(PCA pca);
}