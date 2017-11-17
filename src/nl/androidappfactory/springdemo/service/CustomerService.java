package nl.androidappfactory.springdemo.service;

import java.util.List;

import nl.androidappfactory.springdemo.entity.Customer;

public interface CustomerService {

	public Customer getCustomer(int id);

	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public void deleteCustomer(int id);
}
