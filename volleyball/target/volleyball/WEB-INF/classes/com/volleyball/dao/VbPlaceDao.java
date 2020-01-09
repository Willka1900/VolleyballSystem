package com.volleyball.dao;

import java.util.List;

import com.volleyball.bean.PCA;
import com.volleyball.bean.VbPlace;

public interface VbPlaceDao {
	int deleteByPrimaryKey(Integer id);

	int insert(VbPlace record);

	int insertSelective(VbPlace record);

	VbPlace selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(VbPlace record);

	int updateByPrimaryKey(VbPlace record);

	// 手写
	List<VbPlace> selectByPCA(PCA pca);
}