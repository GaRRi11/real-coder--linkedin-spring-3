package com.realcoders.realcoderlinkedinspring3.customer;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CustomerService {

    public boolean notNull(CustomerRegistrationRequest customerRegistrationRequest){
        return customerRegistrationRequest.getUsername() != null &&
                customerRegistrationRequest.getEmail() != null &&
                customerRegistrationRequest.getPassword() != null &&
                customerRegistrationRequest.getFullname() != null;
    }

    public boolean emailIsAvailable(HashMap<Integer,Customer> customers,
                                    CustomerRegistrationRequest customerRegistrationRequest){
        for (int i = 1; i <= customers.size(); i++) {
            if (customers.get(i).getEmail().equals(customerRegistrationRequest.getEmail())){
                return false;
            }
        }
        return true;
    }
}
