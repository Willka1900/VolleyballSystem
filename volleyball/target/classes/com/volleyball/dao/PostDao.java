package com.volleyball.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.volleyball.bean.Post;

public interface PostDao {
	int deleteByPrimaryKey(Integer id);

	int insert(Post record);

	int insertSelective(Post record);

	Post selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Post record);

	int updateByPrimaryKeyWithBLOBs(Post record);

	int updateByPrimaryKey(Post record);

	// 手写
	List<Post> selectByVbPlaceId(String vbPlaceId);

	List<Post> selectByTitle(@Param("vbplaceid") String vbPlaceId, @Param("title") String title);

	List<Post> selectByFollowId(Integer followid);

	List<Post> selectById(Integer id);

	Post searchId(@Param("publisherId") String publisherId, @Param("title") String title);
	
	int deleteByFollowId(Integer followid);
}