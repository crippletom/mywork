package com.xx.auth.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.xx.auth.entity.AuthUser;
import com.xx.auth.service.AuthUserService;
import com.xx.auth.shiro.authc.MyAuthenticationToken;
import com.xx.common.tools.PropertyTools;

/**
 * 
 * @author quqiang
 *
 */
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private AuthUserService authUserService;

	/**
	 * 授权查询回调函数
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		AuthUser user=(AuthUser)SecurityUtils.getSubject().getSession().getAttribute(PropertyTools.AUTH_USER);
		if(user==null){
			return null;
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//加载权限信息
		info.addRoles(authUserService.loadUserRoles(user.getUserId()));
		info.addStringPermissions(authUserService.loadUserPermissions(user.getUserId()));
		return info;
	}

	/**
	 * 认证回调函数，登录信息和用户验证信息验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//
		MyAuthenticationToken upToken = (MyAuthenticationToken) token;
		Session session = SecurityUtils.getSubject().getSession();
		//校验验证码
		if(PropertyTools.isVerifyCode()){
			String verifyCode=(String)session.getAttribute(PropertyTools.VERIFY_CODE);
			if(null!=verifyCode && verifyCode.equals(upToken.getVerifyCode())){
			}else{
				throw new AuthenticationException("验证码错误");
			}
		}
		AuthUser user = new AuthUser();
		user.setUsername(upToken.getUsername());
		user.setPassword(String.valueOf(upToken.getPassword()));
		// 登录
		AuthUser userLogin = authUserService.loadUser(user);
		if (userLogin == null){
			throw new AuthenticationException("用户名或密码错误");
		}else if(userLogin.getStatus().equals(AuthUser.USER_LOCKED)){
			throw new AuthenticationException("用户被锁定");
		}
		// 设置session
		session.setAttribute(PropertyTools.AUTH_USER, userLogin);
		// 当前 Realm 的 name
		String realmName = this.getName();
		Object principal = upToken.getPrincipal();
		return new SimpleAuthenticationInfo(principal, userLogin.getPassword(),realmName);
	}

}
