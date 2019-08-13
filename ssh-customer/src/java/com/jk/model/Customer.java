package com.jk.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="t_customer")
public class Customer implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1531256584592814065L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String description;
	
	private String name;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date create_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date gj_time;
	
	private String phone;
	
	private Integer department_id;

	private Integer location_id;
	
	public Integer getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Integer location_id) {
		this.location_id = location_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getGj_time() {
		return gj_time;
	}

	public void setGj_time(Date gj_time) {
		this.gj_time = gj_time;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", description=" + description + ", name=" + name + ", create_time=" + create_time
				+ ", gj_time=" + gj_time + ", phone=" + phone + ", department_id=" + department_id + "]";
	}
	

	

}
