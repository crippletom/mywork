package com.xx.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="AUTH_PERMISSION")
@Entity
public class AuthPermission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6911342293568985284L;

	private Long permissionId;
	private Integer type;
	private Long resourceId;
	private String code;
	
	@Id
	@GeneratedValue(generator="seqauthpermission",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seqauthpermission",sequenceName="SEQ_AUTH_PERMISSION")
	@Column(name="PERMISSION_ID")
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	@Column(name="TYPE",length=1)
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name="RESOURCE_ID")
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
