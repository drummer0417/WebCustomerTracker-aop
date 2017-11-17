package nl.androidappfactory.springdemo.dao;

import java.util.List;

import nl.androidappfactory.springdemo.entity.Customer;

public interface CustomerDao {

	public Customer getCustomer(int id);

	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public void deleteCustomer(int id);

}
