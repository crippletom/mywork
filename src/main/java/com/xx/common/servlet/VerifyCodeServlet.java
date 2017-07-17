package com.xx.common.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xx.common.tools.PropertyTools;
import com.xx.common.tools.VerifyCodeTools;

/**
 * 图片验证码
 * @author quqiang
 *
 */
public class VerifyCodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1704370627275292878L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
          
        //生成随机字串  
        String verifyCode = VerifyCodeTools.generateVerifyCode(4);  
        //存入会话session  
        HttpSession session = request.getSession(true);  
        session.setAttribute(PropertyTools.VERIFY_CODE, verifyCode.toLowerCase());  
        //生成图片  
        int w = 100, h = 40;  
        VerifyCodeTools.outputImage(w, h, response.getOutputStream(), verifyCode);
	}

}
