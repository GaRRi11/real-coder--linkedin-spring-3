package com.realcoders.realcoderlinkedinspring3.customer;

import java.util.function.Function;

public class CustomerDTOMapper implements Function<Customer,CustomerDTO> {

    @Override
    public CustomerDTO apply(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getFullname(),
                customer.getAge()
        );
    }
}
