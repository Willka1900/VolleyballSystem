package com.volleyball.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.volleyball.bean.test;
import com.volleyball.dao.testMapper;
import com.volleyball.service.ITestService;

/** 
 * @author liwenxue
 * @date 创建时间：2019年7月13日 下午5:25:11 
 * @version 1.0 
 **/
@Service
public class ITestServiceImpl implements ITestService{

	@Resource
	private testMapper dao;
	
	@Override
	public test getUserById(String test) {
		// TODO Auto-generated method stub
		return this.dao.selectByPrimaryKey(test);
	}

}
