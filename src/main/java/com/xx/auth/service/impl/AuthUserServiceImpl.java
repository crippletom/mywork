package com.xx.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.auth.entity.AuthPermission;
import com.xx.auth.entity.AuthRole;
import com.xx.auth.entity.AuthUser;
import com.xx.auth.repository.AuthUserRepository;
import com.xx.auth.service.AuthUserService;

/**
 * 
 * @author quqiang
 *
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {
	
	@Autowired
	private AuthUserRepository repository;

	@Override
	public AuthUser loadUser(AuthUser user) {
		String sql="select * from auth_user where user_name = ? and password = ? ";
		List<Object> params=new ArrayList<Object>();
		params.add(user.getUsername());
		params.add(user.getPassword());
		List<AuthUser> users=repository.getFromNativeSQL(sql, AuthUser.class, params.toArray());
		if(null==users||users.size()==0){
			return null;
		}
		return users.get(0);
	}

	@Override
	public List<String> loadUserRoles(Long userId) {
		
		String sql="select r.* from auth_role r,auth_user_role ur where r.role_id=ur.role_id and and ur.user_id = ? ";
		List<Object> params=new ArrayList<Object>();
		params.add(userId);
		List<AuthRole> roleList=repository.getFromNativeSQL(sql, AuthRole.class, params.toArray());
		List<String> roles=new ArrayList<String>();
		for(AuthRole role:roleList){
			roles.add(role.getCode());
		}
		return roles;
	}

	@Override
	public List<String> loadUserPermissions(Long userId) {
		String sql="select distinct p.* from auth_permission p, auth_permission_grant pg,auth_user_role ur where p.permission_id=pg.permission_id and pg.role_id=ur.role_id and ur.user_id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(userId);
		List<AuthPermission> permList=repository.getFromNativeSQL(sql, AuthPermission.class, params.toArray());
		List<String> perms=new ArrayList<String>();
		for(AuthPermission ap:permList){
			perms.add(ap.getCode());
		}
		return perms;
	}

}
