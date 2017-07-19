package com.xx.log.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xx.auth.entity.AuthUser;
import com.xx.common.tools.PropertyTools;
import com.xx.log.annotation.AccessLogFunc;
import com.xx.log.entity.AccessLog;
import com.xx.log.service.AccessLogService;

/**
 * 记录部分标记的访问日志
 * @author quqiang
 *
 */
public class AccessLogInterceptor extends HandlerInterceptorAdapter implements ApplicationContextAware{
	
	private AccessLogService logService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HandlerMethod method = (HandlerMethod) handler;
		AccessLogFunc alf=method.getMethodAnnotation(AccessLogFunc.class);
		HttpSession session=request.getSession(false);
		AuthUser user=null;
		if(alf!=null && session!=null && 
				(user=(AuthUser)session.getAttribute(PropertyTools.AUTH_USER))!=null){
			AccessLog log=new AccessLog();
			log.setUserName(user.getUsername());
			log.setFuncDesc(alf.value());
			log.setAccessTime(new Date());
			//TODO 请求参数
			logService.add(log);
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		logService=(AccessLogService)applicationContext.getBean("accessLogService");
	}

}
