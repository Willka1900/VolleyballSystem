package com.volleyball.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.volleyball.bean.Admin;
import com.volleyball.bean.User;
import com.volleyball.service.AdminService;
import com.volleyball.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class LoginController {
	@Autowired
	AdminService adminService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/reg")
	public String toReg(HttpServletRequest request) {

		return "reg";

	}

	// 0:用户昵称已存在 1:用户邮箱已注册 2:用户注册成功 3:用户注册失败
	@RequestMapping(value = "/api/regCheck", method = RequestMethod.POST)
	public @ResponseBody Object regCheck(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		String email = request.getParameter("email");
		String passwd = request.getParameter("password");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		HashMap<String, String> res = new HashMap<String, String>();
		if (null != userService.getByName(name)) {
			res.put("stateCode", "0");
		} else if (null != userService.getByEmail(email)) {
			res.put("stateCode", "1");
		} else {
			User userInsert = new User();
			userInsert.setEmail(email);
			userInsert.setName(name);
			userInsert.setPassword(passwd);
			userInsert.setSex(Integer.parseInt(sex));
			boolean flag = false;
			try {
				flag = userService.insertUser(userInsert);
			} catch (Exception e) {
				System.out.println(e);
			}
			if (flag) {
				User user = userService.getByName(name);
				Cookie cookie = new Cookie("userId", "" + user.getId());
				cookie.setMaxAge(3600 * 24);
				httpServletResponse.addCookie(cookie);
				request.getSession().setAttribute("user", user);
				res.put("stateCode", "2");
			} else {
				res.put("stateCode", "3");
			}
		}
		return res;
	}

	@RequestMapping(value = "/login")
	public String toIndex(HttpServletRequest request) {

		return "login";

	}

	// 0:用户不存在 1:密码错误 2:登陆成功 3:用户被冻结
	@RequestMapping(value = "/api/loginCheck", method = RequestMethod.POST)
	public @ResponseBody Object loginCheck(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		String email = request.getParameter("email");
		String passwd = request.getParameter("password");
		String type = request.getParameter("type");
		HashMap<String, String> res = new HashMap<String, String>();
		if (type.equals("admin")) {
			Admin admin = adminService.getByEmail(email);
			if (admin == null) {
				res.put("stateCode", "0");
			} else if (!admin.getPassword().equals(passwd)) {
				res.put("stateCode", "1");
			} else {
				Cookie cookie = new Cookie("adminId", "" + admin.getId());
				cookie.setMaxAge(3600 * 24);
				httpServletResponse.addCookie(cookie);
				request.getSession().setAttribute("admin", admin);
				res.put("stateCode", "2");
			}
		} else {
			User user = userService.getByEmail(email);
			if (user == null) {
				res.put("stateCode", "0");
			} else if (!user.getPassword().equals(passwd)) {
				res.put("stateCode", "1");
			} else {
				if (user.getStatus() == 0) {// 用户状态为"正常"
					Cookie cookie = new Cookie("userId", "" + user.getId());
					cookie.setMaxAge(3600 * 24);
					httpServletResponse.addCookie(cookie);
					request.getSession().setAttribute("user", user);
					res.put("stateCode", "2");
				} else {// 用户状态为"冻结"
					res.put("stateCode", "3");
				}
			}
		}
		return res;
	}

	@RequestMapping(value = { "/user/logout" })
	public String userLogout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		return "redirect:/";
	}

	@RequestMapping(value = { "/admin/logout" })
	public String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("admin");
		return "redirect:/";
	}

}
