package com.cwca.customer.system.controller;
import com.cwca.customer.common.service.SysShiroService;
import com.cwca.customer.common.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysLoginController {
	@Autowired
	private SysShiroService loginService;
	@RequestMapping("/loginUI.do")
	public String login(){
		return "login";
	}
	/**登录操作*/
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult login(String username, String password,Boolean rememberMe){
		System.out.println(username+"/"+password);
	    loginService.login(username, password,rememberMe);
		return new JsonResult();
	}
/*
	@RequestMapping("/guestlogin.do")
	@ResponseBody
	public JsonResult guestlogin(String role){
			loginService.guestlogin(role);
			return new JsonResult();

	}*/

}
