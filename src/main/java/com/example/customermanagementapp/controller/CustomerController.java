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

    // Displaying all the customers from the database in home page
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("ListCustomer" , customerService.getAllCustomers());
        return "index";
    }

    // Once the Add customer button is clicked we will show the new_customer html page
    @GetMapping("/showNewCustomerForm")
    public String showNewCustomerForm(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer" , customer);
        return "new_customer";
    }

    // Once the save customer button is clicked we will get all the details
    // using model and store it in the db
    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    // Once the update button is clicked we have at all info of
    // customer and then display it in update_customer page for
    // user to edit it
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") int id, Model model) {

        // get customer from the service
        Customer customer = customerService.getCustomerById(id);

        // set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        return "update_customer";
    }

    // Once the delete button is pressed it will pass the customer id as
    // path variable we use that id and delete it from the database
    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") int id){

        customerService.deleteCustomer(id);
        return "redirect:/";
    }
}
