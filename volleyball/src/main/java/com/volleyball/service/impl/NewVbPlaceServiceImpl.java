package com.volleyball.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volleyball.bean.NewVbPlace;
import com.volleyball.bean.PCA;
import com.volleyball.bean.VbPlace;
import com.volleyball.dao.NewVbPlaceDao;
import com.volleyball.dao.VbPlaceDao;
import com.volleyball.redis.RedisUtils;
import com.volleyball.service.NewVbPlaceService;

/**
 * @author liwenxue
 * @date 创建时间：2019年10月31日 上午10:39:20
 * @version 1.0
 **/
@Service
public class NewVbPlaceServiceImpl implements NewVbPlaceService {

	@Autowired
	public NewVbPlaceDao newVbPlaceDao;
	@Autowired
	public VbPlaceDao vbPlaceDao;
	@Autowired
	public RedisUtils redis;

	@Override
	public List<NewVbPlace> selectByPCA(PCA pca) {
		return newVbPlaceDao.selectByPCA(pca);
	}

	@Override
	public boolean insertNewVbPlace(VbPlace VbPlace) throws Exception {
		int flag = vbPlaceDao.insertSelective(VbPlace);
		if (flag != 0) {
			PCA pca = VbPlace.toPCA();
			try {
				redis.hdel(pca.getProvince(), pca.toAll(), pca.toCity(), pca.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag == 0 ? false : true;
	}

	@Override
	public NewVbPlace selectById(Integer id) {
		return newVbPlaceDao.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateNewVbPlace(NewVbPlace newVbPlace) throws Exception {
		int flag = newVbPlaceDao.updateByPrimaryKeySelective(newVbPlace);
		return flag == 0 ? false : true;
	}

}
