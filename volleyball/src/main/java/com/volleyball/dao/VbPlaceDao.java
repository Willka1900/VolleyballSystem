package com.volleyball.dao;

import java.util.List;

import com.volleyball.bean.PCA;
import com.volleyball.bean.VbPlace;

public interface VbPlaceDao {
	int deleteByPrimaryKey(Integer id);

	int insert(VbPlace record);

	int insertSelective(VbPlace record);

	VbPlace selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(VbPlace record);

	int updateByPrimaryKey(VbPlace record);

	// 手写
	List<VbPlace> selectByPCA(PCA pca);
	
	/**贴数+1
	 * @param id
	 * @return
	 */
	int updatePostNum(Integer id);
	
	/**人气值+1
	 * @param id
	 * @return
	 */
	int updatePeople(Integer id);
}