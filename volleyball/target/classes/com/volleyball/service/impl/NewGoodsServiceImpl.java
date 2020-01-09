package com.volleyball.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volleyball.bean.Goods;
import com.volleyball.bean.NewGoods;
import com.volleyball.dao.GoodsDao;
import com.volleyball.dao.NewGoodsDao;
import com.volleyball.service.NewGoodsService;

/**
 * @author liwenxue
 * @date 创建时间：2019年11月3日 下午3:06:53
 * @version 1.0
 **/
@Service
public class NewGoodsServiceImpl implements NewGoodsService {

	@Autowired
	public NewGoodsDao newGoodsDao;
	@Autowired
	public GoodsDao goodsDao;

	@Override
	public List<NewGoods> selectByKind(Integer kind) {
		// TODO Auto-generated method stub
		return newGoodsDao.selectByKind(kind);
	}

	@Override
	public NewGoods selectById(Integer id) {
		// TODO Auto-generated method stub
		return newGoodsDao.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateNewGoods(NewGoods newGoods) throws Exception {
		// TODO Auto-generated method stub
		int flag = newGoodsDao.updateByPrimaryKeySelective(newGoods);
		return flag == 0 ? false : true;
	}

	@Override
	public boolean insertNewGoods(Goods goods) throws Exception {
		// TODO Auto-generated method stub
		int flag = goodsDao.insertSelective(goods);
		return flag == 0 ? false : true;
	}

}
