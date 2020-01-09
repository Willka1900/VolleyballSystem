package com.volleyball.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volleyball.bean.PCA;
import com.volleyball.bean.User;
import com.volleyball.bean.VbPlace;
import com.volleyball.service.UserService;

/**
 * @author liwenxue
 * @date 创建时间：2019年11月6日 下午5:47:33
 * @version 1.0
 **/
@Controller
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Resource
	public UserService userService;

	@RequestMapping("/userManage")
	public ModelAndView userManage(@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize, @ModelAttribute("User") User user,
			@ModelAttribute("name") String name, @ModelAttribute("email") String email,
			@ModelAttribute("sex") String sex, @ModelAttribute("status") String status) {
		if (null == user.getName() && !sex.isEmpty() && !status.isEmpty()) {
			user.setName(name);
			user.setEmail(email);
			user.setSex(Integer.parseInt(sex));
			user.setStatus(Integer.parseInt(status));
		}
		ModelAndView modelAndView = new ModelAndView("admin/userManage");
		PageHelper.startPage(page, pageSize);// 此方法后跟着的第一个查询方法会被插件分页
		List<User> users = userService.selectByUser(user);// 查询所有符合条件的用户
		PageInfo<User> pageInfo = new PageInfo<User>(users);
		modelAndView.addObject("users", users);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequestMapping("/changeStatus")
	public String changeStatus(@RequestParam("id") Integer id, @RequestParam("status") Integer status,
			@RequestParam("userStatus") Integer userStatus, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("sex") Integer sex, @RequestParam("page") Integer page,
			RedirectAttributes attributes) {
		User updateUser = new User();
		updateUser.setId(id);
		updateUser.setStatus(status);
		try {
			logger.debug("更改用户状态开始");
			userService.updateUser(updateUser);
			logger.debug("更改用户状态结束");
		} catch (Exception e) {
			System.out.println(e);
		}
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setSex(sex);
		user.setStatus(userStatus);
		attributes.addFlashAttribute("User", user);
		attributes.addFlashAttribute("page", page);
		return "redirect:/userManage";
	}

	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam("id") Integer id, @RequestParam("status") Integer status,
			@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("sex") Integer sex,
			@RequestParam("page") Integer page, RedirectAttributes attributes) {
		try {
			logger.debug("删除用户开始");
			userService.deleteById(id);
			logger.debug("删除用户结束");
		} catch (Exception e) {
			System.out.println(e);
		}
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setSex(sex);
		user.setStatus(status);
		attributes.addFlashAttribute("User", user);
		attributes.addFlashAttribute("page", page);
		return "redirect:/userManage";
	}
}
