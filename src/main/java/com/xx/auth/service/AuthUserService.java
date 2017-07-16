package com.xx.auth.service;

import java.util.List;

import com.xx.auth.entity.AuthUser;

/**
 * 
 * @author quqiang
 *
 */
public interface AuthUserService {
	
	/**
	 * 验证用户信息，用于登录
	 * @param user
	 * @return
	 */
	public AuthUser loadUser(AuthUser user);
	
	/**
	 * 获取用户的角色列表
	 * @param userId
	 * @return
	 */
	public List<String> loadUserRoles(Long userId);
	
	/**
	 * 获取用户的权限列表
	 * @param userId
	 * @return
	 */
	public List<String> loadUserPermissions(Long userId);

}
