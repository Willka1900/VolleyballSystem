package com.volleyball.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volleyball.bean.NewVbPlace;
import com.volleyball.bean.PCA;
import com.volleyball.bean.VbPlace;
import com.volleyball.dao.NewVbPlaceDao;
import com.volleyball.dao.VbPlaceDao;
import com.volleyball.service.NewVbPlaceService;

/** 
 * @author liwenxue
 * @date 创建时间：2019年10月31日 上午10:39:20 
 * @version 1.0 
 **/
@Service
public class NewVbPlaceServiceImpl implements NewVbPlaceService{

	@Autowired
	public NewVbPlaceDao newVbPlaceDao;
	@Autowired
	public VbPlaceDao vbPlaceDao;
	@Override
	public List<NewVbPlace> selectByPCA(PCA pca) {
		// TODO Auto-generated method stub
		return newVbPlaceDao.selectByPCA(pca);
	}
	@Override
	public boolean insertNewVbPlace(VbPlace VbPlace) throws Exception{
		// TODO Auto-generated method stub
		int flag = vbPlaceDao.insertSelective(VbPlace);
		return flag==0?false:true;
	}
	@Override
	public NewVbPlace selectById(Integer id) {
		// TODO Auto-generated method stub
		return newVbPlaceDao.selectByPrimaryKey(id);
	}
	@Override
	public boolean updateNewVbPlace(NewVbPlace newVbPlace) throws Exception {
		// TODO Auto-generated method stub
		int flag = newVbPlaceDao.updateByPrimaryKeySelective(newVbPlace);
		return flag==0?false:true;
	}


}
