package com.volleyball.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.volleyball.bean.Article;
import com.volleyball.service.ArticleService;

/** 
 * @author liwenxue
 * @date 创建时间：2019年7月14日 上午11:48:01 
 * @version 1.0 
 **/
@Controller
public class IndexController {
	
	private static Logger logger = Logger.getLogger(IndexController.class); 
	
	@Resource
	public ArticleService articleService;
	
	@RequestMapping("/")
	public ModelAndView index(@RequestParam(required=true,defaultValue="1") Integer page, @RequestParam(required=false,defaultValue="5") Integer pageSize){
		ModelAndView modelAndView =new ModelAndView("index");
        PageHelper.startPage(page, pageSize);
        List<Article> articles=articleService.queryAll();
        PageInfo<Article> pageInfo=new PageInfo<Article>(articles);
        modelAndView.addObject("articles",articles);
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
	}
	
	@RequestMapping("/about")
    public String about(){
        return "about";
    }
}
