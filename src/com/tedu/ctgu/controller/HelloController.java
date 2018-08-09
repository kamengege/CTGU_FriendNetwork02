package com.tedu.ctgu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tedu.ctgu.pojo.User;

@Controller
@RequestMapping("/demo")
public class HelloController{
	@Resource   
	private UserDaoTest userDaoTest;
	
	@RequestMapping("/hello.do")
	public String sayhello(HttpServletRequest request){
		System.out.println("second MVC");
		return "/hello";
	}
	
	@RequestMapping("/login.do")
	@ResponseBody
	public ModelAndView helloSubmit(
			@RequestParam("name")String name,
			@RequestParam("age")String age){
		//1.ԭʼ��ʽ request
		//String name=request.getParameter("name");
		//request.setAttribute("name", name);
		//2.ʹ�ò���ע��ķ�ʽ :@requestParam
		Map<String, String> map=new HashMap<String,
				String>();
		map.put("name", name);
		map.put("age", age);
		return new ModelAndView("/list",map);
		//������,ֱ�Ӵ��ݶ���,���¸�����
	}
	@RequestMapping("/login1.do")
	public ModelAndView helloSubmit1(User user,
			HttpServletRequest request){
		System.out.println(user);
		userDaoTest.registerUser(user);
		return new ModelAndView("/list");
	}
	
	@RequestMapping("/toRegist.do")
	public String toRegist(){
		return "/friend_demo/regist";
	}
	//�ض���,Ĭ�ϵĶ���ת��
	@RequestMapping("/toRegist1.do")
	public String toRegist1(){
		new ModelAndView(
				new RedirectView("login1.do"));
		return "redirect:login1.do";
	}

}
