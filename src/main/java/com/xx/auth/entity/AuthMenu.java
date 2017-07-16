package com.xx.auth.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="AUTH_MENU")
@Entity
public class AuthMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6025696693630471186L;

	private Long menuId;
	private Long parentId;
	private String code;
	private String name;
	
	@Id
	@GeneratedValue(generator="seqauthmenu",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seqauthmenu",sequenceName="SEQ_AUTH_MENU")
	@Column(name="MENU_ID")
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	@Column(name="PARENT_ID")
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	
	//
	private List<AuthMenuOperate> operateList=new ArrayList<AuthMenuOperate>();

	@JoinColumn(name="MENU_ID")
	@OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	public List<AuthMenuOperate> getOperateList() {
		return operateList;
	}
	public void setOperateList(List<AuthMenuOperate> operateList) {
		this.operateList = operateList;
	}
	
}
