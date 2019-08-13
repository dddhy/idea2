package com.jk.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jk.dao.CusDao;
import com.jk.model.Customer;

@Repository
public class CusDaoImpl implements CusDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> queryCustomer(String hql) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(hql);
		
		return query.list();
	}

	@Override
	public void addCus(Customer customer) {
		
		
		Session session = sessionFactory.getCurrentSession();
		session.save(customer);
	}

	@Override
	public void deleteCus(String hql) {
		System.err.println(hql);
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(hql);
		
		query.executeUpdate();
	}
}
