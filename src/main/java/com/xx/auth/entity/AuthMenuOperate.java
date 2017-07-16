package com.xx.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="AUTH_MENU_OPERATE")
@Entity
public class AuthMenuOperate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2033777194921825057L;

	private Long operateId;
	private Long menuId;
	private String code;
	private String name;
	
	@Id
	@GeneratedValue(generator="seqauthmenuoperate",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seqauthmenuoperate",sequenceName="SEQ_AUTH_MENU_OPERATE")
	@Column(name="OPERATE_ID")
	public Long getOperateId() {
		return operateId;
	}
	public void setOperateId(Long operateId) {
		this.operateId = operateId;
	}
	
	@Column(name="MENU_ID")
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
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
	
}
