package com.example.customermanagementapp.controller;

import com.example.customermanagementapp.models.Customer;
import com.example.customermanagementapp.service.CustomerService;
import com.example.customermanagementapp.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;

    // Method for displaying list of employees
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("ListCustomer" , customerService.getAllCustomers());
        return "index";
    }

    @GetMapping("/showNewCustomerForm")
    public String showNewCustomerForm(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer" , customer);
        return "new_customer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") int id, Model model) {

        // get employee from the service
        Customer customer = customerService.getCustomerById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        return "update_customer";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") int id){

        customerService.deleteCustomer(id);
        return "redirect:/";
    }
}
