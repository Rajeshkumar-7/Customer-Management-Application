package com.example.customermanagementapp.service;


import com.example.customermanagementapp.models.Customer;
import com.example.customermanagementapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        // Get all the customers from the database using inbuilt method
        return customerRepository.findAll();
    }

    @Override
    public void saveCustomer(Customer customer) {
        // Save the customer to database
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(int id) {
        // Get the customer from the database using id
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = null;
        if(optionalCustomer.isPresent()){
            customer = optionalCustomer.get();
        }else{
            throw new RuntimeException("Employee not found for ID : " + id);
        }
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        // Delete the customer form the database
        customerRepository.deleteById(id);
    }
}
