package com.xx.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="AUTH_ROLE")
@Entity
public class AuthRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3060837283712074517L;

	private Long roleId;
	private String code;
	private String name;
	private String description;
	
	@Id
	@GeneratedValue(generator="seqauthrole",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seqauthrole",sequenceName="SEQ_AUTH_ROLE")
	@Column(name="ROLE_ID")
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
