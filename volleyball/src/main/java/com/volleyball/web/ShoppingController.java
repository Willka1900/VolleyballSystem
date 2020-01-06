package com.volleyball.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volleyball.bean.Goods;
import com.volleyball.bean.NewGoods;
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

	@RequestMapping("/uploadGoods")
	public ModelAndView uploadGoods(@ModelAttribute("NewGoods") NewGoods newGoods) {
		ModelAndView modelAndView = new ModelAndView("uploadGoods");
		if (null != newGoods.getName()) {
			HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
					.getSession();// 通过监听器获取request再获取session
			Integer userId = ((User) session.getAttribute("user")).getId();// 获取session中的用户id
			modelAndView = new ModelAndView("redirect:/shopping");
			newGoods.setUploadTime(new Date());
			newGoods.setUploaderId(String.valueOf(userId));
			try {
				goodsService.uploadNewGoods(newGoods);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return modelAndView;
	}

}
