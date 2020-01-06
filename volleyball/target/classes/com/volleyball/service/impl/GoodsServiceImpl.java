package com.volleyball.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volleyball.bean.Goods;
import com.volleyball.bean.NewGoods;
import com.volleyball.dao.GoodsDao;
import com.volleyball.dao.NewGoodsDao;
import com.volleyball.service.GoodsService;

/**
 * @author liwenxue
 * @date 创建时间：2019年11月3日 上午10:57:09
 * @version 1.0
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	public GoodsDao goodsDao;
	@Autowired
	public NewGoodsDao newGoodsDao;

	@Override
	public boolean uploadNewGoods(NewGoods record) throws Exception {
		// TODO Auto-generated method stub
		int flag = newGoodsDao.uploadNewGoods(record);
		return flag == 0 ? false : true;
	}

	@Override
	public List<Goods> selectByKind(Integer kind) {
		// TODO Auto-generated method stub
		return goodsDao.selectByKind(kind);
	}

	@Override
	public Goods selectById(Integer id) {
		// TODO Auto-generated method stub
		return goodsDao.selectByPrimaryKey(id);
	}

}
