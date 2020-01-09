package com.volleyball.service;

import java.util.List;

import com.volleyball.bean.NewVbPlace;
import com.volleyball.bean.PCA;
import com.volleyball.bean.VbPlace;

/** 
 * @author liwenxue
 * @date 创建时间：2019年10月29日 下午8:07:03 
 * @version 1.0 
 **/
public interface NewVbPlaceService {
	
	/**在审核区列出目标区域内的所有球场
	 * @param pca
	 * @return
	 */
	List<NewVbPlace> selectByPCA(PCA pca);
	
	/**向前台插入新球场
	 * @param vbPlace
	 * @return
	 * @throws Exception 
	 */
	boolean insertNewVbPlace(VbPlace VbPlace) throws Exception;
	
	/**根据id查找待审核球场信息
	 * @param id
	 * @return
	 */
	NewVbPlace selectById(Integer id);
	
	/**审核新球场信息后更新状态
	 * @param newVbPlace
	 * @return
	 * @throws Exception
	 */
	boolean updateNewVbPlace(NewVbPlace newVbPlace) throws Exception;
}
