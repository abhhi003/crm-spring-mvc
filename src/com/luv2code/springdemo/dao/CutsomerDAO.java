package com.luv2code.springdemo.dao;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CutsomerDAO {

	public List<Customer> getCustomers();

	/**
	 * @param theCustomer 
	 */
	public void saveCustomer(Customer theCustomer);

	/**
	 * @param theId
	 * @return
	 */
	public Customer getCustomer(int theId);

	/**
	 * @param theId
	 */
	public void deleteCustomer(int theId);

	/**
	 * @param theName
	 * @return
	 */
	public List<Customer> getCustomerForName(String theName);
}
