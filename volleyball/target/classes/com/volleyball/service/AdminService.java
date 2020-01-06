package com.volleyball.service;

import com.volleyball.bean.Admin;

public interface AdminService {
	/**根据id获取管理员信息
	 * @param id
	 * @return
	 */
	Admin getById(Integer id);
	
	/**根据email获取管理员信息
	 * @param email
	 * @return
	 */
	Admin getByEmail(String email);

}
