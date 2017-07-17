package com.xx.common.exception;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.xx.common.tools.JsonTools;
import com.xx.common.vo.JsonResult;

/**
 * 全局异常处理
 * @author quqiang
 *
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	/**
	 * 对于异常的处理
	 * 如果是AJAX请求，则通过输出流返回数据进行提示
	 * 如果不是则返回视图进行提示
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mav=null;
		JsonResult result=new JsonResult();
		result.setStatusCode(JsonResult.FAILURE);
		String header = request.getHeader("X-Requested-With");
		if(null!=header&&header.equals("XMLHttpRequest")){//ajax请求
			response.setCharacterEncoding("UTF-8");
			Writer writer=null;
			try {
				writer=response.getWriter();
				if(ex instanceof UnauthorizedException){
					result.setMessage("没有访问资源的权限！");
				}else if(ex instanceof BusinessException){
					result.setMessage(ex.getMessage());
				}
				writer.write(JsonTools.toJson(result));
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			if(ex instanceof UnauthorizedException){
				mav=new ModelAndView("unauthorized");
			}else if(ex instanceof AuthenticationException){
				mav=new ModelAndView("login");
				mav.addObject("errorMsg", ex.getMessage());
			}
		}
		return mav;
	}

}
