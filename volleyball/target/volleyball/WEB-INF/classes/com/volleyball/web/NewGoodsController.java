package com.volleyball.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volleyball.bean.Admin;
import com.volleyball.bean.Goods;
import com.volleyball.bean.NewGoods;
import com.volleyball.bean.NewVbPlace;
import com.volleyball.bean.PCA;
import com.volleyball.bean.VbPlace;
import com.volleyball.service.GoodsService;
import com.volleyball.service.NewGoodsService;
import com.volleyball.service.NewVbPlaceService;
import com.volleyball.service.VbPlaceService;

/**
 * @author liwenxue
 * @date 创建时间：2019年7月14日 上午11:48:01
 * @version 1.0
 **/
@Controller
public class NewGoodsController {

	private static Logger logger = Logger.getLogger(NewGoodsController.class);

	@Resource
	public GoodsService goodsService;
	@Resource
	public NewGoodsService newGoodsService;

	@RequestMapping("/goodsAudit")
	public ModelAndView newGoods(@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "4") Integer pageSize,
			@RequestParam(defaultValue = "5") Integer kind) {
		ModelAndView modelAndView = new ModelAndView("admin/goodsAudit");
		PageHelper.startPage(page, pageSize);// 此方法后跟着的第一个查询方法会被插件分页
		List<NewGoods> newGoods = newGoodsService.selectByKind(kind);// 查询所有符合条件的被上传球场
		PageInfo<NewGoods> pageInfo = new PageInfo<NewGoods>(newGoods);
		modelAndView.addObject("newGoods", newGoods);
		modelAndView.addObject("pageInfo", pageInfo);
		return modelAndView;
	}

	@RequestMapping("/auditGoods")
	public String auditGoods(@RequestParam("id") Integer id, @RequestParam("audit") String audit) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getSession();// 通过监听器获取request再获取session
		Integer auditId = (Integer) ((Admin) session.getAttribute("admin")).getId();// 获取session中的管理员id
		NewGoods updateNewGoods = new NewGoods();
		updateNewGoods.setId(id);
		updateNewGoods.setAuditId(String.valueOf(auditId));
		updateNewGoods.setAuditTime(new Date());
		NewGoods newGoods = newGoodsService.selectById(id);
		if (audit.equals("pass")) {
			updateNewGoods.setIsaudited(1);
			Goods goods = new Goods();
			goods.setKind(newGoods.getKind());
			goods.setName(newGoods.getName());
			goods.setPrice(newGoods.getPrice());
			goods.setImg(newGoods.getImg());
			goods.setPublisherId(newGoods.getUploaderId());
			goods.setPublishTime(newGoods.getUploadTime());
			try {
				newGoodsService.updateNewGoods(updateNewGoods);// 更新新用品表信息
				newGoodsService.insertNewGoods(goods);// 向前台插入新用品信息
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			updateNewGoods.setIsaudited(2);
			try {
				newGoodsService.updateNewGoods(updateNewGoods);// 更新新用品表信息
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return "redirect:/goodsAudit?kind=" + newGoods.getKind() + "";
	}

}
