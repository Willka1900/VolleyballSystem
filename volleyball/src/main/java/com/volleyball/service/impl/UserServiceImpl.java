package com.volleyball.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volleyball.bean.User;
import com.volleyball.dao.UserDao;
import com.volleyball.service.UserService;

/**
 * @author liwenxue
 * @date 创建时间：2019年10月31日 下午5:37:07
 * @version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserDao userDao;

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.selectByEmail(email);
	}

	@Override
	public User getByName(String name) {
		// TODO Auto-generated method stub
		return userDao.selectByName(name);
	}

	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		int flag = userDao.insertSelective(user);
		return flag == 0 ? false : true;
	}

	@Override
	public List<User> selectByUser(User user) {
		// TODO Auto-generated method stub
		return userDao.selectByUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		int flag = userDao.updateByPrimaryKeySelective(user);
		return flag == 0 ? false : true;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		int flag = userDao.deleteByPrimaryKey(id);
		return flag == 0 ? false : true;
	}

}
