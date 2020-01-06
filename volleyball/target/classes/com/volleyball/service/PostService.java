package com.volleyball.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.volleyball.bean.Post;

/** 
 * @author liwenxue
 * @date 创建时间：2019年11月5日 下午2:53:09 
 * @version 1.0 
 **/
public interface PostService {
	/**根据球场id查询该球场帖区信息
	 * @param vbPlaceId
	 * @return
	 */
	List<Post> selectByVbPlaceId(String vbPlaceId);
	
	/**根据标题查询目标帖子信息
	 * @param title
	 * @return
	 */
	List<Post> selectByTitle(String vbPlaceId,String title);
	
	/**发表帖子
	 * @param post
	 * @return
	 */
	boolean insertPost(Post post);
	
	/**根据跟帖id查找
	 * @param vbplaceid
	 * @return
	 */
	List<Post> selectByFollowId(Integer followid);
	
	/**根据帖子id查找
	 * @param id
	 * @return
	 */
	List<Post> selectById(Integer id);
	
	/**根据发帖人id和发帖标题查找帖子id
	 * @param publisherId
	 * @param publishTime
	 * @return
	 */
	Post searchId(String publisherId, String title);
	
	/**根据id更新帖子信息
	 * @param id
	 * @return
	 */
	boolean updateById(Post post);
	
	/**根据id删除帖子详情信息
	 * @param id
	 * @return
	 */
	boolean deleteById(Integer id);
	
	/**根据跟帖id删除帖子
	 * @param followId
	 * @return
	 */
	boolean deleteByFollowId(Integer followId);
}
