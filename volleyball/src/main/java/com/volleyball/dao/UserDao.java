package com.volleyball.dao;

import java.util.List;

import com.volleyball.bean.User;

public interface UserDao {
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	// 手写
	User selectByEmail(String email);

	User selectByName(String name);

	List<User> selectByUser(User user);
}