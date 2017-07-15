package com.xx.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="DEMO")
@Entity
public class Demo {
	
	private Long id;
	private String name;
	private Date birthday;
	private Integer gender;
	private Date createTime;
	
	public Demo() {}
	
	public Demo(String name, Integer gender) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthday=new Date();
		this.createTime=new Date();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqdemo")
	@SequenceGenerator(name="seqdemo",sequenceName="SEQ_DEMO")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
