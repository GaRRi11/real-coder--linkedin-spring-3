package com.realcoders.realcoderlinkedinspring3.controller;

import com.realcoders.realcoderlinkedinspring3.customer.Customer;
import com.realcoders.realcoderlinkedinspring3.customer.CustomerRegistrationRequest;
import com.realcoders.realcoderlinkedinspring3.customer.CustomerService;
import com.realcoders.realcoderlinkedinspring3.exceptions.EmailAlreadyExistsException;
import com.realcoders.realcoderlinkedinspring3.exceptions.NullPointerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class Controller {

    private final CustomerService customerService;

    HashMap<Integer, Customer> savedCustomers = new HashMap<>();

    @Autowired
    public Controller(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        if (!customerService.notNull(customerRegistrationRequest)){
            throw new NullPointerException("The request was malformed or missing required fields");
        }
        if (!customerService.emailIsAvailable(savedCustomers,customerRegistrationRequest)){
            throw new EmailAlreadyExistsException("The specified username or email already exists");
        }
        Customer customer = new Customer(
                customerRegistrationRequest.getUsername(),
                customerRegistrationRequest.getEmail(),
                customerRegistrationRequest.getPassword(),
                customerRegistrationRequest.getFullname(),
                customerRegistrationRequest.getAge()
        );
        savedCustomers.put(customer.getId(), customer);
        System.out.println(savedCustomers.get(customer.getId()).getId());
        return "The user was successfully registered";

    }

}
