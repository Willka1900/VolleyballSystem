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
import com.volleyball.bean.Post;
import com.volleyball.bean.User;
import com.volleyball.service.PostService;

/**
 * @author liwenxue
 * @date 创建时间：2019年7月14日 上午11:48:01
 * @version 1.0
 **/
@Controller
public class PostController {

	private static Logger logger = Logger.getLogger(PostController.class);

	@Resource
	public PostService postService;

	// 跳转并搜索帖子
	@RequestMapping("/post")
	public ModelAndView post(@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(required = false, value = "vbPlaceId") String vbPlaceId,
			@RequestParam(required = false, value = "title") String title) {
		ModelAndView modelAndView = new ModelAndView("post");
		PageHelper.startPage(page, pageSize);// 此方法后跟着的第一个查询方法会被插件分页
		List<Post> post;
		if (null != title && "" != title) {
			post = postService.selectByTitle(vbPlaceId, title);// 查询该球场下目标标题的帖子
		} else {
			post = postService.selectByVbPlaceId(vbPlaceId);// 查询该球场下的帖子
		}
		PageInfo<Post> pageInfo = new PageInfo<Post>(post);
		modelAndView.addObject("post", post);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.addObject("vbPlaceId", vbPlaceId);
		return modelAndView;
	}

	// 发帖
	@RequestMapping("/submitPost")
	public String submitPost(@ModelAttribute("Post") Post post) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getSession();// 通过监听器获取request再获取session
		Integer userId = ((User) session.getAttribute("user")).getId();// 获取session中的用户id
		post.setPublisherId(String.valueOf(userId));
		post.setPublishTime(new Date());
		try {
			postService.insertPost(post);
			Post firstPost = new Post();
			int id = postService.searchId(String.valueOf(userId), post.getTitle()).getId();
			firstPost.setId(id);
			firstPost.setFollowid(id);
			postService.updateById(firstPost);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/post?vbPlaceId=" + post.getVbplaceid();
	}

	// 跳转至帖子详情
	@RequestMapping("/postDetail")
	public ModelAndView postDetail(@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(required = false, value = "followid") Integer followid,
			@RequestParam(required = false, value = "vbPlaceId") String vbPlaceId) {
		ModelAndView modelAndView = new ModelAndView("postDetails");
		PageHelper.startPage(page, pageSize);// 此方法后跟着的第一个查询方法会被插件分页
		List<Post> postDetails = postService.selectByFollowId(followid);// 根据跟帖id查询所有该帖信息
		PageInfo<Post> pageInfo = new PageInfo<Post>(postDetails);
		modelAndView.addObject("postDetails", postDetails);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.addObject("vbPlaceId", vbPlaceId);
		return modelAndView;
	}

	// 跟帖
	@RequestMapping("/submitPostDetail")
	public String submitPostDetail(@ModelAttribute("PostDetail") Post post) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getSession();// 通过监听器获取request再获取session
		Integer userId = ((User) session.getAttribute("user")).getId();// 获取session中的用户id
		post.setPublisherId(String.valueOf(userId));
		post.setPublishTime(new Date());
		try {
			logger.debug("插入跟帖信息结束");
			postService.insertPost(post);
			logger.debug("插入跟帖信息结束");
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/postDetail?followid=" + post.getFollowid() + "&vbPlaceId=" + post.getVbplaceid();
	}

	// 管理帖子
	@RequestMapping("/postManage")
	public ModelAndView postManage(@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(required = false, value = "title") String title) {
		ModelAndView modelAndView = new ModelAndView("admin/postManage");
		PageHelper.startPage(page, pageSize);// 此方法后跟着的第一个查询方法会被插件分页
		List<Post> post = postService.selectByTitle("", title);// 查询目标标题帖子
		PageInfo<Post> pageInfo = new PageInfo<Post>(post);
		modelAndView.addObject("post", post);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.addObject("title", title);
		return modelAndView;
	}

	// 删除帖子
	@RequestMapping("/postDelete")
	public String postDelete(@RequestParam("followid") Integer followid, @RequestParam("title") String title) {
		try {
			logger.debug("删除帖子开始");
			postService.deleteByFollowId(followid);
			logger.debug("删除帖子结束");
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/postManage?title=" + title;
	}

	// 管理帖子详情
	@RequestMapping("/postDetailManage")
	public ModelAndView postDetailManage(@RequestParam(required = true, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(required = false, value = "followid") Integer followid,
			@RequestParam(required = false, value = "title") String title) {
		ModelAndView modelAndView = new ModelAndView("admin/postDetailManage");
		PageHelper.startPage(page, pageSize);// 此方法后跟着的第一个查询方法会被插件分页
		List<Post> postDetails = postService.selectByFollowId(followid);// 根据跟帖id查询所有该帖信息
		PageInfo<Post> pageInfo = new PageInfo<Post>(postDetails);
		modelAndView.addObject("postDetails", postDetails);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.addObject("title", title);
		return modelAndView;
	}

	@RequestMapping("/postDetailDelete")
	public String postDetailDelete(@RequestParam("id") Integer id, @RequestParam("followid") Integer followid,
			@RequestParam("title") String title) {
		try {
			logger.debug("删除帖子详情开始");
			postService.deleteById(id);
			logger.debug("删除帖子详情结束");
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/postDetailManage?followid=" + followid + "&title=" + title;
	}
}
