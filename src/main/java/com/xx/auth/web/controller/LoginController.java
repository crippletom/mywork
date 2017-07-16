package com.xx.auth.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xx.auth.entity.AuthUser;
import com.xx.auth.exception.IncorrectNamePasswordException;
import com.xx.auth.exception.UserLockedException;

/**
 * 
 * @author quqiang
 *
 */
@Controller
public class LoginController {
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String doLogin(AuthUser user,Model model){
		Subject subject=SecurityUtils.getSubject();
		if(subject.isAuthenticated()){//已经登录
			return "redirect:index";
		}
		//登录
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword().toCharArray(),"");
		try {
			subject.login(token);
			return "redirect:index";
		} catch (IncorrectNamePasswordException e) {
			model.addAttribute("errorMsg", "用户名或密码错误");
		} catch (UserLockedException e) {
			model.addAttribute("errorMsg", "用户被锁定");
		} catch (AuthenticationException e) {
			model.addAttribute("errorMsg", "登录失败");
			e.printStackTrace();
		}
		return "redirect:login";
	}
}
