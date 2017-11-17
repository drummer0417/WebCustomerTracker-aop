package nl.androidappfactory.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.androidappfactory.springdemo.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		// get session
		Session session = sessionFactory.getCurrentSession();

		// create query
		Query<Customer> query = session.createQuery("from Customer order by firstName, lastName", Customer.class);

		// get customers
		List<Customer> customers = query.getResultList();

		System.out.println("get all customers: " + customers);

		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {

		// get session
		Session session = sessionFactory.getCurrentSession();

		// save or update (depending on new user of existing... prim key?) customer
		session.saveOrUpdate(customer);

		System.out.println("saved: " + customer);

	}

	@Override
	public Customer getCustomer(int id) {
		// get session
		Session session = sessionFactory.getCurrentSession();

		// save customer
		Customer customer = session.get(Customer.class, id);

		return customer;
	}

	@Override
	public void deleteCustomer(int id) {

		// get session
		Session session = sessionFactory.getCurrentSession();

		// create delete query
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);

		// exec query
		query.executeUpdate();
	}
}
