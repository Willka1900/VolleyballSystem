package com.volleyball.service;

import java.util.List;

import com.volleyball.bean.User;

/** 
 * @author liwenxue
 * @date 创建时间：2019年10月31日 下午5:35:41 
 * @version 1.0 
 **/
public interface UserService {
	/**根据id获取用户信息
	 * @param id
	 * @return
	 */
	User getById(Integer id);
	
	/**根据email获取用户信息
	 * @param email
	 * @return
	 */
	User getByEmail(String email);
	
	/**根据昵称获取用户信息
	 * @param name
	 * @return
	 */
	User getByName(String name);
	
	/**插入新用户
	 * @param user
	 * @return
	 */
	boolean insertUser(User user);
	
	/**根据user对象信息查找user列表
	 * @param user
	 * @return
	 */
	List<User> selectByUser(User user);
	
	/**根据user对象更新user对象信息
	 * @param user
	 * @return
	 */
	boolean updateUser(User user);
	
	/**根据id删除用户
	 * @param id
	 * @return
	 */
	boolean deleteById(Integer id);
}
