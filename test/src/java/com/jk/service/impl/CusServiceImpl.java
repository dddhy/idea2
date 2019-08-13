package com.jk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.CusDao;
import com.jk.model.Customer;
import com.jk.service.CusService;

@Service
public class CusServiceImpl implements CusService {

	@Autowired
	private CusDao cusDao;

	@Override
	public List<Customer> queryCustomer() {
		StringBuffer sf =new StringBuffer("select new map(c.id as id,c.name as cname,d.dname as dname,c.create_time as create_time,c.gj_time as gj_time,c.phone as phone,l.lname as lname) FROM Customer c,Department d,Locations l where c.location_id = l.id and c.department_id = d.id");
		//StringBuffer sf = new StringBuffer(" FROM Customer c,Department d,Locations l where c.location_id = l.id and c.department_id = d.id");
		
		return cusDao.queryCustomer(sf.toString());
	}

	@Override
	public void addCus(Customer customer) {

		cusDao.addCus(customer);
	}

	@Override
	public void deleteCus(Integer id) {
		System.err.println(id);
		StringBuffer sf =new StringBuffer(" delete from Customer where id = "+id+"");
		cusDao.deleteCus(sf.toString());
	}
	
	
}
