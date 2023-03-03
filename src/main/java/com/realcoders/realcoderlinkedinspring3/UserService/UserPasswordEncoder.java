package com.realcoders.realcoderlinkedinspring3.UserService;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserPasswordEncoder {

    public String encode(String  password){
        System.out.println(Base64.getEncoder().withoutPadding().encodeToString(password.getBytes()));
        return Base64.getEncoder().withoutPadding().encodeToString(password.getBytes());
    }
}
