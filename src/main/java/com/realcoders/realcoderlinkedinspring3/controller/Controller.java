package com.realcoders.realcoderlinkedinspring3.controller;

import com.realcoders.realcoderlinkedinspring3.UserService.UserService;
import com.realcoders.realcoderlinkedinspring3.config.UserContext;
import com.realcoders.realcoderlinkedinspring3.exceptions.EmailAlreadyExistsException;
import com.realcoders.realcoderlinkedinspring3.exceptions.NullPointerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.IllegalFormatCodePointException;


@RestController
public class Controller {

    private final UserService userService;

    private final UserDTOMapper userDTOMapper;



    @Autowired
    public Controller(UserService userService, UserDTOMapper userDTOMapper) {
        this.userService = userService;
        this.userDTOMapper = userDTOMapper;
    }


    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse register(@RequestBody UserRegistrationDTO userRegistrationDTO)
            throws NoSuchAlgorithmException{
        if (
                userRegistrationDTO.getUsername() == null ||
                        userRegistrationDTO.getEmail() == null ||
                        userRegistrationDTO.getFullname() == null ||
                        userRegistrationDTO.getPassword() == null
        ){
            throw new NullPointerException("The request was malformed or missing required fields");

        }
        if (userService.findByEmail(userRegistrationDTO.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("The specified username or email already exists");
        }
        return userService.register(userDTOMapper.fromDTO(userRegistrationDTO));
//        return "The user was successfully registered" + "  token: " + token;

    }
    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws NoSuchAlgorithmException {
        if (
                request.getUsername() == null ||
                        request.getPassword() == null
        ){
            throw new NullPointerException("The request was malformed or missing required fields");
        }
        return ResponseEntity.ok(userService.authenticate(request));

    }

    @PostMapping("/companies/dummy")
    public String createCompany(@RequestBody CompaniCreationRequest companiCreationRequest){
        if (companiCreationRequest.getName() == null){
            throw new NullPointerException("The request was malformed or missing required fields");
        }

        return "The company was successfully created.";
    }

    @GetMapping("/logged/test")
    public String test(){

        return "test";
    }

}
