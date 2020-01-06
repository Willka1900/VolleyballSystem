package com.volleyball.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.volleyball.bean.test;
import com.volleyball.service.ITestService;


/** 
 * @author liwenxue
 * @date 创建时间：2019年7月14日 上午11:48:01 
 * @version 1.0 
 **/
@Controller
@RequestMapping("/zou")
public class testController {
	
	private static Logger logger = Logger.getLogger(testController.class); 
	
	@Resource
	public ITestService testService;
	
	@RequestMapping("/test")
	public ModelAndView toIndex(){
		ModelAndView mav = new ModelAndView("/kaokaoni");
		test qwe = testService.getUserById("666");
		mav.addObject("qu",qwe);
		logger.debug("后台完成，准备跳转");;
		return mav;
	}
}
