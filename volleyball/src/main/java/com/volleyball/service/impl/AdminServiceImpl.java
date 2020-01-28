package com.volleyball.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volleyball.bean.Admin;
import com.volleyball.dao.AdminDao;
import com.volleyball.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	public AdminDao adminDao;

	
	public Admin getById(Integer id) {
		return adminDao.selectByPrimaryKey(id);
	}

	@Override
	public Admin getByEmail(String email) {
		// TODO Auto-generated method stub
		return adminDao.selectByEmail(email);
	}
}
