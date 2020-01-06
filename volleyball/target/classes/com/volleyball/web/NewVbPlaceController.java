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
import com.volleyball.bean.NewVbPlace;
import com.volleyball.bean.PCA;
import com.volleyball.bean.VbPlace;
import com.volleyball.service.NewVbPlaceService;
import com.volleyball.service.VbPlaceService;

/**
 * @author liwenxue
 * @date 创建时间：2019年7月14日 上午11:48:01
 * @version 1.0
 **/
@Controller
public class NewVbPlaceController {

	private static Logger logger = Logger.getLogger(NewVbPlaceController.class);

	@Resource
	public VbPlaceService vbPlaceService;
	@Resource
	public NewVbPlaceService newVbPlaceService;

	@RequestMapping("/newVbPlace")
	public ModelAndView newVbPlace(@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("PCA") PCA pca) {
		ModelAndView modelAndView = new ModelAndView("admin/vbPlaceAudit");
		PageHelper.startPage(page, pageSize);// 此方法后跟着的第一个查询方法会被插件分页
		List<NewVbPlace> newVbPlaces = newVbPlaceService.selectByPCA(pca);// 查询所有符合条件的被上传球场
		PageInfo<NewVbPlace> pageInfo = new PageInfo<NewVbPlace>(newVbPlaces);
		modelAndView.addObject("newVbPlaces", newVbPlaces);
		modelAndView.addObject("pageInfo", pageInfo);
		return modelAndView;
	}

	@RequestMapping("/auditVbPlace")
	public String auditVbPlace(@RequestParam("id") Integer id, @RequestParam("audit") String audit,
			RedirectAttributes attributes) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getSession();// 通过监听器获取request再获取session
		Integer auditId = ((Admin) session.getAttribute("admin")).getId();// 获取session中的管理员id
		NewVbPlace updateNewVbPlace = new NewVbPlace();
		updateNewVbPlace.setId(id);
		updateNewVbPlace.setAuditId(String.valueOf(auditId));
		updateNewVbPlace.setAuditTime(new Date());
		NewVbPlace newVbPlace = newVbPlaceService.selectById(id);
		if (audit.equals("pass")) {
			updateNewVbPlace.setIsaudited(1);
			VbPlace vbPlace = new VbPlace();
			vbPlace.setProvince(newVbPlace.getProvince());
			vbPlace.setCity(newVbPlace.getCity());
			vbPlace.setArea(newVbPlace.getArea());
			vbPlace.setLocation(newVbPlace.getLocation());
			vbPlace.setPublisherId(newVbPlace.getUploaderId());
			vbPlace.setPublishTime(newVbPlace.getUploadTime());
			try {
				newVbPlaceService.updateNewVbPlace(updateNewVbPlace);// 更新新球场表信息
				newVbPlaceService.insertNewVbPlace(vbPlace);// 向前台插入球场信息
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			updateNewVbPlace.setIsaudited(2);
			try {
				newVbPlaceService.updateNewVbPlace(updateNewVbPlace);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		PCA pca = new PCA();
		pca.setProvince(newVbPlace.getProvince());
		pca.setCity(newVbPlace.getCity());
		pca.setArea(newVbPlace.getArea());
		attributes.addFlashAttribute("PCA", pca);
		return "redirect:/newVbPlace";
	}
}
