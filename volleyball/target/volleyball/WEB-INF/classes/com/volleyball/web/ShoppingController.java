package com.volleyball.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volleyball.bean.Goods;
import com.volleyball.bean.NewGoods;
import com.volleyball.bean.NewVbPlace;
import com.volleyball.bean.User;
import com.volleyball.service.GoodsService;
import com.volleyball.service.NewGoodsService;

/**
 * @author liwenxue
 * @date 创建时间：2019年11月3日 上午10:31:34
 * @version 1.0
 **/
@Controller
public class ShoppingController {

	private static Logger logger = Logger.getLogger(ShoppingController.class);

	@Autowired
	public GoodsService goodsService;

	@Autowired
	public NewGoodsService newGoodsService;

	@RequestMapping("/shopping")
	public ModelAndView shopping(@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "4") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer kind) {
		ModelAndView modelAndView = new ModelAndView("shopping");
		PageHelper.startPage(page, pageSize);
		List<Goods> goods = goodsService.selectByKind(kind);
		PageInfo<Goods> pageInfo = new PageInfo<Goods>(goods);
		modelAndView.addObject("goods", goods);
		modelAndView.addObject("pageInfo", pageInfo);
		return modelAndView;
	}

	@RequestMapping("/toUploadGoods")
	public String toUploadGoods(HttpServletRequest request) {

		return "uploadGoods";
	}

	@RequestMapping(value = "/uploadGoods", method = RequestMethod.POST)
	public ModelAndView uploadGoods(NewGoods newGoods, @RequestParam("file") MultipartFile pic, ModelMap map)
			throws IOException {
		/* 上传图片 */
		// 设置一下保存的路径
		String path = "E:/pic/goods/";
		File dir = new File(path);
		if (!dir.isDirectory())
			dir.mkdir();
		// 给文件一个新的名字
		String filename = UUID.randomUUID().toString().replaceAll("-", "");
		// 获取文件的扩展名
		String ext = FilenameUtils.getExtension(pic.getOriginalFilename());
		try {
			// 把文件存到指定的位置
			pic.transferTo(new File(path + filename + "." + ext));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		newGoods.setImg(filename + "." + ext);
		// 通过获取session来获取当前登录用户id
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getSession();// 通过监听器获取request再获取session
		Integer userId = ((User) session.getAttribute("user")).getId();// 获取session中的用户id
		ModelAndView modelAndView = new ModelAndView("redirect:/shopping");
		newGoods.setUploadTime(new Date());
		newGoods.setUploaderId(String.valueOf(userId));
		try {
			goodsService.uploadNewGoods(newGoods);
		} catch (Exception e) {
			System.out.println(e);
		}
		return modelAndView;
	}

}
