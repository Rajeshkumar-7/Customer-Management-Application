package com.example.customermanagementapp.service;

import com.example.customermanagementapp.models.Customer;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {

    List<Customer> getAllCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomerById(int id);

    void deleteCustomer(int id);
}
