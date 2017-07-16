package com.xx.auth.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author quqiang
 *
 */
@Table(name="AUTH_USER")
@Entity
public class AuthUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7263843293200573333L;
	
	public static final String AUTH_USER="AUTH_USER";
	
	private Long userId;
	private String username;
	private String password;
	private Integer status;
	private Date createTime;
	
	public AuthUser(){}

	public AuthUser(String userName) {
		super();
		this.username = userName;
		this.status=1;
		this.createTime=new Date();
	}

	@Id
	@GeneratedValue(generator="seqauthuser",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seqauthuser",sequenceName="SEQ_AUTH_USER")
	@Column(name="USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name="USER_NAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="STATUS",length=1)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
