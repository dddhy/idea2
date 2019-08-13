package com.jk.dao;

import java.util.List;

import com.jk.model.Customer;

public interface CusDao {

	List<Customer> queryCustomer(String hql);

	void addCus(Customer customer);

	void deleteCus(String hql);

}
