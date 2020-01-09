package com.volleyball.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volleyball.bean.Post;
import com.volleyball.dao.PostDao;
import com.volleyball.service.PostService;

/**
 * @author liwenxue
 * @date 创建时间：2019年11月5日 下午3:09:19
 * @version 1.0
 **/
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	public PostDao postDao;

	@Override
	public List<Post> selectByVbPlaceId(String vbPlaceId) {
		// TODO Auto-generated method stub
		return postDao.selectByVbPlaceId(vbPlaceId);
	}

	@Override
	public List<Post> selectByTitle(String vbPlaceId, String title) {
		// TODO Auto-generated method stub
		return postDao.selectByTitle(vbPlaceId, title);
	}

	@Override
	public boolean insertPost(Post post) {
		// TODO Auto-generated method stub
		int flag = postDao.insertSelective(post);
		return flag == 0 ? false : true;
	}

	@Override
	public List<Post> selectByFollowId(Integer followid) {
		// TODO Auto-generated method stub
		return postDao.selectByFollowId(followid);
	}

	@Override
	public List<Post> selectById(Integer id) {
		// TODO Auto-generated method stub
		return postDao.selectById(id);
	}

	@Override
	public Post searchId(String publisherId, String title) {
		// TODO Auto-generated method stub
		return postDao.searchId(publisherId, title);
	}

	@Override
	public boolean updateById(Post post) {
		// TODO Auto-generated method stub
		int flag = postDao.updateByPrimaryKeySelective(post);
		return flag == 0 ? false : true;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		int flag = postDao.deleteByPrimaryKey(id);
		return flag == 0 ? false : true;
	}

	@Override
	public boolean deleteByFollowId(Integer followId) {
		// TODO Auto-generated method stub
		int flag = postDao.deleteByFollowId(followId);
		return flag == 0 ? false : true;
	}

}
