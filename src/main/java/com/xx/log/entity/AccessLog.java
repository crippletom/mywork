package com.xx.log.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 访问日志
 * @author quqiang
 *
 */
@Table(name="AUTH_ACCESS_LOG")
@Entity
public class AccessLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3440322320317368292L;
	
	private Long accessId;
	private String userName;
	private String funcDesc;
	private String params;
	private Date accessTime;
	
	@Id
	@GeneratedValue(generator="seqauthaccesslog",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seqauthaccesslog",sequenceName="SEQ_AUTH_ACCESS_LOG")
	@Column(name="ACCESS_ID")
	public Long getAccessId() {
		return accessId;
	}
	public void setAccessId(Long accessId) {
		this.accessId = accessId;
	}
	
	@Column(name="USER_NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="FUNC_DESC")
	public String getFuncDesc() {
		return funcDesc;
	}
	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}
	
	@Column(name="PARAMS")
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	
	@Column(name="ACCESS_TIME")
	public Date getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}
	
	@Override
	public String toString() {
		return "AccessLog [accessId=" + accessId + ", userName=" + userName
				+ ", funcDesc=" + funcDesc + ", params=" + params
				+ ", accessTime=" + accessTime + "]";
	}
	
}
