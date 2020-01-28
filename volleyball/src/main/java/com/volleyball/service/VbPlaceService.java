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
public interface VbPlaceService {
	
	/**列出目标区域内的所有球场
	 * @param pca
	 * @return
	 */
	List<VbPlace> selectByPCA(PCA pca);
	
	/**向后台上传新球场
	 * @param vbPlace
	 * @return
	 */
	boolean uploadNewVbPlace(NewVbPlace newVbPlace);
	
	/**根据球场id查找球场信息
	 * @param id
	 * @return
	 */
	VbPlace selectById(Integer id);
	
	/**更新球场信息
	 * @param vbPlace
	 * @return
	 */
	boolean updateVbPlace(VbPlace vbPlace);
}
