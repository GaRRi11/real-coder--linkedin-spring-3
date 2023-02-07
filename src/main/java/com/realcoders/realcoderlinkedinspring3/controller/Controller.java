package com.realcoders.realcoderlinkedinspring3.controller;

import com.realcoders.realcoderlinkedinspring3.user.*;
import com.realcoders.realcoderlinkedinspring3.exceptions.EmailAlreadyExistsException;
import com.realcoders.realcoderlinkedinspring3.exceptions.NullPointerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {

    private final UserService userService;

    @Autowired
    public Controller(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        if (
                userRegistrationRequest.getUsername() == null ||
                        userRegistrationRequest.getEmail() == null ||
                        userRegistrationRequest.getFullname() == null ||
                        userRegistrationRequest.getPassword() == null
        ){
            throw new NullPointerException("The request was malformed or missing required fields");

        }
        if (userService.findByEmail(userRegistrationRequest.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("The specified username or email already exists");
        }
        UserDTO userDTO = new UserDTO(
                userRegistrationRequest.getUsername(),
                userRegistrationRequest.getEmail(),
                userRegistrationRequest.getFullname(),
                userRegistrationRequest.getPassword(),
                userRegistrationRequest.getAge()
        );
        userService.save(userDTO);
        return "The user was successfully registered";

    }

}
