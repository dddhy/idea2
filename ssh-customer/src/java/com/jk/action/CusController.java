package com.jk.action;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSON;
import com.jk.model.Customer;
import com.jk.service.CusService;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action("cusAction")
@Results({
	@Result(name="list",location = "/list.jsp"),
})
public class CusController extends BaseAction implements ModelDriven<Customer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8048046896298960165L;

	@Autowired
	private CusService cusService;
	
	private Integer id;
	
	private String name;
	
	private String description;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date create_time;
	
	private String phone;
	
	private Integer department_id;

	private Integer location_id;
	
	private Customer customer;
	
	private Customer customer2 = new Customer();
	
	
	public String queryCustomer(){
		
		List<Customer> list = cusService.queryCustomer();
		
		String jsonStringWithDateFormat = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd");
		
		super.writeJson(list);
		
		return "list";
	}
	
	public void addCus(){
		Customer customer3 = new Customer();
		customer3.setName(name);
		customer3.setPhone(phone);
		customer3.setLocation_id(location_id);
		customer3.setDepartment_id(department_id);
		customer3.setId(department_id);
		customer3.setCreate_time(create_time);
		System.err.println(name);
		System.err.println(customer);
		System.err.println(customer2);
		cusService.addCus(customer3);
	}

	public void deleteCus(){
		System.err.println(id);
		cusService.deleteCus(id);
	}
	
	
	

	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CusService getCusService() {
		return cusService;
	}

	public void setCusService(CusService cusService) {
		this.cusService = cusService;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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

	public Integer getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Integer location_id) {
		this.location_id = location_id;
	}
	
	
}
