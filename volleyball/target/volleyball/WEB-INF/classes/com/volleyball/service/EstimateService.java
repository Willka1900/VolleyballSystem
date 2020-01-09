package com.volleyball.service;

import java.util.List;

import com.volleyball.bean.Estimate;

/** 
 * @author liwenxue
 * @date 创建时间：2019年11月5日 下午10:09:36 
 * @version 1.0 
 **/
public interface EstimateService {
	/**根据用品id查找对应评价信息
	 * @param goodsId
	 * @return
	 */
	List<Estimate> selectByGoodsId(String goodsId);
	
	/**插入评价
	 * @param estimate
	 * @return
	 */
	boolean insertEstimate(Estimate estimate);
}
