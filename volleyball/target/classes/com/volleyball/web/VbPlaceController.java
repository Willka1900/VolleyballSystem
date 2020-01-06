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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volleyball.bean.NewVbPlace;
import com.volleyball.bean.PCA;
import com.volleyball.bean.User;
import com.volleyball.bean.VbPlace;
import com.volleyball.service.NewVbPlaceService;
import com.volleyball.service.VbPlaceService;

/**
 * @author liwenxue
 * @date 创建时间：2019年7月14日 上午11:48:01
 * @version 1.0
 **/
@Controller
public class VbPlaceController {

	private static Logger logger = Logger.getLogger(VbPlaceController.class);

	@Resource
	public VbPlaceService vbPlaceService;
	@Resource
	public NewVbPlaceService newVbPlaceService;

	@RequestMapping("/vbPlace")
	public ModelAndView vbPlace(@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("PCA") PCA pca,
			@RequestParam(required = false, value = "vbPlaceId") String vbPlaceId) {
		ModelAndView modelAndView = new ModelAndView("vbPlace");
		if (null != vbPlaceId && "" != vbPlaceId) {
			VbPlace vbplace = vbPlaceService.selectById(Integer.parseInt(vbPlaceId));
			pca.setProvince(vbplace.getProvince());
			pca.setCity(vbplace.getCity());
			pca.setArea(vbplace.getArea());
		}
		PageHelper.startPage(page, pageSize);// 此方法后跟着的第一个查询方法会被插件分页
		List<VbPlace> vbPlaces = vbPlaceService.selectByPCA(pca);// 查询所有符合条件的球场
		PageInfo<VbPlace> pageInfo = new PageInfo<VbPlace>(vbPlaces);
		modelAndView.addObject("vbPlaces", vbPlaces);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.addObject("PCA", pca);
		return modelAndView;
	}

	@RequestMapping("/uploadVbPlace")
	public ModelAndView uploadVbPlace(@ModelAttribute("NewVbPlace") NewVbPlace newVbPlace) {
		ModelAndView modelAndView = new ModelAndView("uploadVbPlace");
		if (null != newVbPlace.getProvince()) {
			HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
					.getSession();// 通过监听器获取request再获取session
			Integer userId = ((User) session.getAttribute("user")).getId();// 获取session中的用户id
			modelAndView = new ModelAndView("redirect:/vbPlace");
			newVbPlace.setUploadTime(new Date());
			newVbPlace.setUploaderId(String.valueOf(userId));
			try {
				vbPlaceService.uploadNewVbPlace(newVbPlace);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return modelAndView;
	}
}
