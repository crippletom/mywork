package com.xx.auth.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xx.auth.shiro.authc.MyAuthenticationToken;
import com.xx.auth.web.vo.UserVo;

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
	public String doLogin(UserVo user,Model model){
		Subject subject=SecurityUtils.getSubject();
		if(subject.isAuthenticated()){//已经登录
		}else{
			//登录
			MyAuthenticationToken token=new MyAuthenticationToken(user.getUsername(),user.getPassword(),user.getVerifyCode());
			subject.login(token);
		}
		return "redirect:index";
		/*try {
			
			return "redirect:index";
		} catch (IncorrectNamePasswordException e) {
			model.addAttribute("errorMsg", "用户名或密码错误");
		} catch (UserLockedException e) {
			model.addAttribute("errorMsg", "用户被锁定");
		} catch (AuthenticationException e) {
			model.addAttribute("errorMsg", "登录失败");
			e.printStackTrace();
		}
		return "redirect:login";*/
	}
}
