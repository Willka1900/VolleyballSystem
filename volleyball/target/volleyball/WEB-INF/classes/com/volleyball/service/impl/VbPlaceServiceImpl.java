package com.volleyball.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volleyball.bean.NewVbPlace;
import com.volleyball.bean.PCA;
import com.volleyball.bean.VbPlace;
import com.volleyball.dao.NewVbPlaceDao;
import com.volleyball.dao.VbPlaceDao;
import com.volleyball.service.VbPlaceService;

/** 
 * @author liwenxue
 * @date 创建时间：2019年10月29日 下午8:07:29 
 * @version 1.0 
 **/
@Service
public class VbPlaceServiceImpl implements VbPlaceService{

	@Autowired
	public VbPlaceDao placeDao;
	@Autowired
	public NewVbPlaceDao newPlaceDao;
	
	@Override
	public List<VbPlace> selectByPCA(PCA pca) {
		// TODO Auto-generated method stub
		return placeDao.selectByPCA(pca);
	}

	@Override
	public boolean uploadNewVbPlace(NewVbPlace newVbPlace) {
		// TODO Auto-generated method stub
		int flag = newPlaceDao.uploadNewVbPlace(newVbPlace);
		return flag==0?false:true;
	}

	@Override
	public VbPlace selectById(Integer id) {
		// TODO Auto-generated method stub
		return placeDao.selectByPrimaryKey(id);
	}

}
