package com.volleyball.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.volleyball.bean.NewVbPlace;
import com.volleyball.bean.PCA;
import com.volleyball.bean.VbPlace;
import com.volleyball.dao.NewVbPlaceDao;
import com.volleyball.dao.VbPlaceDao;
import com.volleyball.redis.RedisUtils;
import com.volleyball.service.VbPlaceService;

/**
 * @author liwenxue
 * @date 创建时间：2019年10月29日 下午8:07:29
 * @version 1.0
 **/
@Service
public class VbPlaceServiceImpl implements VbPlaceService {

	@Autowired
	public VbPlaceDao placeDao;
	@Autowired
	public NewVbPlaceDao newPlaceDao;
	@Autowired
	public RedisUtils redis;

	private static final Integer expTime = 43200;

	@Override
	public List<VbPlace> selectByPCA(PCA pca) {
		List<VbPlace> list = null;
		if (StringUtils.isBlank(pca.getProvince()) || "请选择省份".equals(pca.getProvince())) {// 省为空时返回所有球场
			/*
			 * try { // 从缓存中获取list String listAllGet = redis.get("allVb"); if
			 * (!StringUtils.isBlank(listAllGet)) { // 把字符串转换成list list =
			 * JSON.parseArray(listAllGet, VbPlace.class); } else { list =
			 * placeDao.selectByPCA(pca); try { redis.set("allVb",
			 * JSON.toJSONString(list)); } catch (Exception e) {
			 * e.printStackTrace(); } } } catch (Exception e) {
			 * e.printStackTrace(); }
			 */
			list = placeDao.selectByPCA(pca);
		} else {// 省非空时返回目标球场
			try {
				// 从缓存中获取list
				String listGet = redis.hget(pca.getProvince(), pca.toString());
				if (!StringUtils.isBlank(listGet)) {
					// 把字符串转换成list
					list = JSON.parseArray(listGet, VbPlace.class);
				} else {
					// 缓存未命中，从数据库中获取
					list = placeDao.selectByPCA(pca);
					try {
						// 设置缓存
						redis.hset(pca.getProvince(), pca.toString(), JSON.toJSONString(list), expTime);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public boolean uploadNewVbPlace(NewVbPlace newVbPlace) {
		// TODO Auto-generated method stub
		int flag = newPlaceDao.uploadNewVbPlace(newVbPlace);
		return flag == 0 ? false : true;
	}

	@Override
	public VbPlace selectById(Integer id) {
		// TODO Auto-generated method stub
		return placeDao.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateVbPlace(VbPlace vbPlace) {
		int flag = placeDao.updateByPrimaryKeySelective(vbPlace);
		return flag == 0 ? false : true;
	}

}
