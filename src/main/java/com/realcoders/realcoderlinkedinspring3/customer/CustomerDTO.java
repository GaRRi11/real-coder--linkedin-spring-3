package com.realcoders.realcoderlinkedinspring3.customer;

public record CustomerDTO(
        Integer id,
        String username,
        String email,
        String password,
        String fullname,
        Integer age
) {
}
