package com.volleyball.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @author liwenxue
 * @date 创建时间：2019年7月14日 上午11:48:01 
 * @version 1.0 
 **/
@Controller
public class IndexController {
	
	private static Logger logger = Logger.getLogger(IndexController.class); 
	
	@RequestMapping("/")
	public String index(){
        return "index";
	}
	
	@RequestMapping("/admin")
	public String adminIndex(){
        return "admin/index";
	}
	
	@RequestMapping("/about")
    public String about(){
        return "about";
    }
}
