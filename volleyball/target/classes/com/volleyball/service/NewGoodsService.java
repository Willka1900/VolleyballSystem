package com.volleyball.service;

import java.util.List;

import com.volleyball.bean.Goods;
import com.volleyball.bean.NewGoods;

/**
 * @author liwenxue
 * @date 创建时间：2019年11月3日 下午3:06:10
 * @version 1.0
 **/
public interface NewGoodsService {
	/**
	 * 根据类别查找用品信息
	 * 
	 * @param kind
	 * @return
	 */
	List<NewGoods> selectByKind(Integer kind);

	/**
	 * 根据新用品id查找新用品信息
	 * 
	 * @param id
	 * @return
	 */
	NewGoods selectById(Integer id);

	/**
	 * 更新新用品表信息
	 * 
	 * @param newGoods
	 * @return
	 * @throws Exception
	 */
	boolean updateNewGoods(NewGoods newGoods) throws Exception;

	/**向前台插入新用品信息
	 * @param goods
	 * @return
	 * @throws Exception 
	 */
	boolean insertNewGoods(Goods goods) throws Exception;
}
