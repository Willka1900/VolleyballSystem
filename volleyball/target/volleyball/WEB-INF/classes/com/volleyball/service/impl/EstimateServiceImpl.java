package com.volleyball.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volleyball.bean.Estimate;
import com.volleyball.dao.EstimateDao;
import com.volleyball.service.EstimateService;

/**
 * @author liwenxue
 * @date 创建时间：2019年11月5日 下午10:11:38
 * @version 1.0
 **/
@Service
public class EstimateServiceImpl implements EstimateService {

	@Autowired
	public EstimateDao estimateDao;

	@Override
	public List<Estimate> selectByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		return estimateDao.selectByGoodsId(goodsId);
	}

	@Override
	public boolean insertEstimate(Estimate estimate) {
		// TODO Auto-generated method stub
		int flag = estimateDao.insertSelective(estimate);
		return flag == 0 ? false : true;
	}

}
