package com.xx.auth.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.xx.auth.entity.AuthUser;
import com.xx.auth.exception.IncorrectNamePasswordException;
import com.xx.auth.exception.UserLockedException;
import com.xx.auth.service.AuthUserService;

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
		AuthUser user=(AuthUser)SecurityUtils.getSubject().getSession().getAttribute(AuthUser.AUTH_USER);
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
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		AuthUser user = new AuthUser();
		user.setUsername(upToken.getUsername());
		user.setPassword(String.valueOf(upToken.getPassword()));
		// 登录
		AuthUser userLogin = authUserService.loadUser(user);
		if (userLogin == null){
			throw new UserLockedException();
		}else if(userLogin.getStatus().equals(0)){
			throw new IncorrectNamePasswordException();
		}
		// 设置session
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(AuthUser.AUTH_USER, userLogin);
		// 当前 Realm 的 name
		String realmName = this.getName();
		Object principal = upToken.getPrincipal();
		return new SimpleAuthenticationInfo(principal, userLogin.getPassword(),realmName);
	}

}
