package com.abc.mobilestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.mobilestore.entity.Customer;
import com.abc.mobilestore.exception.ResourceNotFoundException;
import com.abc.mobilestore.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if(optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not found with id: " + customerId);
		}
		return optionalCustomer.get();
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		
		Optional<Customer> optionalMobile = customerRepository.findById(customer.getCustomerId());
		if (optionalMobile.isEmpty()) {
			throw new ResourceNotFoundException("Mobile not existing with id: " + customer.getCustomerId());
		}		
		
		customerRepository.save(customer);		
		return customer;
	}
 
	@Override
	public void deleteCustomer(int customerId) {
 
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Mobile not existing with id: " + customerId);
		}		
		Customer customer = optionalCustomer.get();		
		customerRepository.delete(customer);
	}
}