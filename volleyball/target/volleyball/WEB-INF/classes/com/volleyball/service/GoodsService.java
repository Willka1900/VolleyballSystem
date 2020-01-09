package com.volleyball.service;

import java.util.List;

import com.volleyball.bean.Goods;
import com.volleyball.bean.NewGoods;

/** 
 * @author liwenxue
 * @date 创建时间：2019年11月3日 上午10:56:11 
 * @version 1.0 
 **/
public interface GoodsService {
	/**根据kind查找用品信息
	 * @param kind
	 * @return
	 */
	List<Goods> selectByKind(Integer kind);

	/**上传新用品信息
	 * @param record
	 * @return
	 * @throws Exception
	 */
	boolean uploadNewGoods(NewGoods record) throws Exception;
	
	/**根据id查找用品信息
	 * @param id
	 * @return
	 */
	Goods selectById(Integer id);
}
