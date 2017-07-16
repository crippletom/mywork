package com.xx.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="AUTH_USER_ROLE")
@Entity
public class AuthUserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4190447134169592282L;

	private Long urId;
	private Long userId;
	private Long roleId;
	
	@Id
	@GeneratedValue(generator="seqauthuserrole",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seqauthuserrole",sequenceName="SEQ_AUTH_USER_ROLE")
	@Column(name="UR_ID")
	public Long getUrId() {
		return urId;
	}
	public void setUrId(Long urId) {
		this.urId = urId;
	}
	
	@Column(name="USER_ID")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="ROLE_ID")
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}
