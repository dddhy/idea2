package com.jk.service;

import java.util.List;

import com.jk.model.Customer;

public interface CusService {

	List<Customer> queryCustomer();

	void addCus(Customer customer);

	void deleteCus(Integer id);

}
