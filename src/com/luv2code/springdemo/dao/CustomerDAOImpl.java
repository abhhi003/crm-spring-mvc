/**
 * 
 */
package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

/**
 * @author Abhishek
 *
 */

@Repository
public class CustomerDAOImpl implements CutsomerDAO {

	//inject the sessionFactory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		//create a query
		//Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);
		
		//create a query to sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", 
				Customer.class);
		
		// execute and get result list
		List<Customer> customers = theQuery.getResultList();
		
		
		//return the list
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save the customer
		currentSession.saveOrUpdate(theCustomer);
		
	}


	@Override
	public Customer getCustomer(int theId) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//get the customer from the db using theId(i.e., primary key)
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete the object with primary key
		Query<Customer> theQuery = currentSession.createQuery("delete from Customer where id=:customerId"); 
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
	}


	@Override
	public List<Customer> getCustomerForName(String theName) {
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = null;
		
		if (theName != null && theName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery = currentSession.createQuery("from Customer", Customer.class);            
        }
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

}
