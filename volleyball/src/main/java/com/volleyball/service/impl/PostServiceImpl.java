package com.volleyball.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.volleyball.bean.PCA;
import com.volleyball.bean.Post;
import com.volleyball.bean.VbPlace;
import com.volleyball.dao.PostDao;
import com.volleyball.dao.VbPlaceDao;
import com.volleyball.redis.RedisUtils;
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
	@Autowired
	public VbPlaceDao vbplaceDao;
	@Autowired
	public RedisUtils redis;

	private static final String POST = "POST";
	private static final String POSTDetails = "POSTDetails";

	@Override
	public List<Post> selectByVbPlaceId(String vbPlaceId) {
		List<Post> list = null;
		try {
			// 从缓存中获取list
			String listGet = redis.hget(POST, vbPlaceId);
			if (!StringUtils.isBlank(listGet)) {
				// 把字符串转换成list
				list = JSON.parseArray(listGet, Post.class);
			} else {
				// 缓存未命中，从数据库中获取
				list = postDao.selectByVbPlaceId(vbPlaceId);
				try {
					// 设置缓存
					redis.hset(POST, vbPlaceId, JSON.toJSONString(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Post> selectByTitle(String vbPlaceId, String title) {
		List<Post> list = null;
		try {
			// 从缓存中获取list
			String listGet = redis.hget(POST, vbPlaceId);
			if (!StringUtils.isBlank(listGet)) {
				// 把字符串转换成list
				List<Post> allList = JSON.parseArray(listGet, Post.class);
				list = new ArrayList<Post>();
				for (Post post : allList) {
					// 如果post中包含了目标title，则放入list中
					if (post.getTitle().contains(title)) {
						list.add(post);
					}
				}
			} else {
				// 缓存未命中，从数据库中获取
				list = postDao.selectByTitle(vbPlaceId, title);
				try {
					// 设置缓存
					redis.hset(POST, vbPlaceId, JSON.toJSONString(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insertPost(Post post) {
		//插入帖子入库
		int flag = postDao.insertSelective(post);
		if (flag != 0) {
			//球场帖子数+1
			int postFlag = vbplaceDao.updatePostNum(Integer.valueOf(post.getVbplaceid()));
			if (postFlag != 0) {
				//查找目标球场信息
				VbPlace vbPlace = vbplaceDao.selectByPrimaryKey(Integer.valueOf(post.getVbplaceid()));
				PCA pca = vbPlace.toPCA();
				try {
					//删除目标球场缓存等待重新加载
					redis.hdel(pca.getProvince(), pca.toAll(), pca.toCity(), pca.toString());
					redis.hdel(POST,post.getVbplaceid());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return flag == 0 ? false : true;
	}

	@Override
	public List<Post> selectByFollowId(Integer followid) {
		List<Post> list = null;
		try {
			// 从缓存中获取list
			String listGet = redis.hget(POSTDetails, String.valueOf(followid));
			if (!StringUtils.isBlank(listGet)) {
				// 把字符串转换成list
				list = JSON.parseArray(listGet, Post.class);
			} else {
				// 缓存未命中，从数据库中获取
				list = postDao.selectByFollowId(followid);
				try {
					// 设置缓存
					redis.hset(POSTDetails, String.valueOf(followid), JSON.toJSONString(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
