package nl.androidappfactory.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nl.androidappfactory.springdemo.entity.Customer;
import nl.androidappfactory.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// inject Service into controller
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomer(Model model) {

		// get customers from customer DAO.
		List<Customer> customers = customerService.getCustomers();
		System.out.println("#customers: " + customers.size());

		// add customers to model
		model.addAttribute("customers", customers);

		// return the view name to be used
		return "list-customers";

	}

	@GetMapping("showFormForAdd")
	public String showFormForAdd(Model model) {

		// create model attribute to bind customer data
		Customer customer = new Customer();

		model.addAttribute("customer", customer);

		//

		return "customer-form";
	}

	@GetMapping("showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {

		// create model attribute to bind customer data
		Customer customer = customerService.getCustomer(id);

		model.addAttribute("customer", customer);

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	private String saveCustomer(@ModelAttribute("customer") Customer customer) {

		customerService.saveCustomer(customer);

		return "redirect:/customer/list";
	}

	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int id) {

		customerService.deleteCustomer(id);

		return "redirect:/customer/list";
	}
}
