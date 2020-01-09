package com.volleyball.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.volleyball.bean.Estimate;
import com.volleyball.bean.Goods;
import com.volleyball.bean.Post;
import com.volleyball.bean.User;
import com.volleyball.service.EstimateService;
import com.volleyball.service.GoodsService;

import org.apache.log4j.Logger;

/**
 * @author liwenxue
 * @date 创建时间：2019年11月5日 下午11:03:18
 * @version 1.0
 **/
@Controller
public class EstimateController {

	private static Logger logger = Logger.getLogger(EstimateController.class);

	@Autowired
	public EstimateService estimateService;

	@Autowired
	public GoodsService goodsService;

	@RequestMapping("/estimate")
	public ModelAndView estimate(@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "4") Integer pageSize,
			@RequestParam(required = false, value = "goodsId") String goodsId) {
		ModelAndView modelAndView = new ModelAndView("estimate");
		PageHelper.startPage(page, pageSize);
		List<Estimate> estimates = estimateService.selectByGoodsId(goodsId);
		PageInfo<Estimate> pageInfo = new PageInfo<Estimate>(estimates);
		Goods goods = goodsService.selectById(Integer.parseInt(goodsId));
		modelAndView.addObject("estimates", estimates);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.addObject("goods", goods);
		return modelAndView;
	}

	@RequestMapping("/submitEstimate")
	public String submitEstimate(@ModelAttribute("Estimate") Estimate estimate) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getSession();// 通过监听器获取request再获取session
		Integer userId = ((User) session.getAttribute("user")).getId();// 获取session中的用户id
		estimate.setPublisherId(String.valueOf(userId));
		estimate.setPublishTime(new Date());
		try {
			logger.debug("插入新评价开始");
			estimateService.insertEstimate(estimate);
			logger.debug("插入新评价结束");
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/estimate?goodsId=" + estimate.getGoodsId();
	}
}
