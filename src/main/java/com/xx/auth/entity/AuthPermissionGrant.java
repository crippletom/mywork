package com.xx.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="AUTH_PERMISSION_GRANT")
@Entity
public class AuthPermissionGrant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7690015831596171465L;

	private Long grantId;
	private Long roleId;
	private Long permissionId;
	
	@Id
	@GeneratedValue(generator="seqauthpermissiongrant",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seqauthpermissiongrant",sequenceName="SEQ_AUTH_PERMISSION_GRANT")
	@Column(name="GRANT_ID")
	public Long getGrantId() {
		return grantId;
	}
	public void setGrantId(Long grantId) {
		this.grantId = grantId;
	}
	
	@Column(name="ROLE_ID")
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	@Column(name="PERMISSION_ID")
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	
}
