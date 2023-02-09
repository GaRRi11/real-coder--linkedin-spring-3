package com.realcoders.realcoderlinkedinspring3.controller;

import com.realcoders.realcoderlinkedinspring3.UserService.AuthenticationService;
import com.realcoders.realcoderlinkedinspring3.UserService.UserService;
import com.realcoders.realcoderlinkedinspring3.exceptions.EmailAlreadyExistsException;
import com.realcoders.realcoderlinkedinspring3.exceptions.NullPointerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {

    private final UserService userService;

    private final UserDTOMapper userDTOMapper;

    private final AuthenticationService authenticationService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public Controller(UserService userService, UserDTOMapper userDTOMapper, AuthenticationService authenticationService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userDTOMapper = userDTOMapper;
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
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
        String token = userService.save(userDTOMapper.fromDTO(userRegistrationDTO));
        return "The user was successfully registered" + "  token: " + token;

    }

    @PostMapping("/auth/register2")
    public ResponseEntity<AuthenticationResponse> register2 (
            @RequestBody UserRegistrationDTO userRegistrationDTO
    ){
        if (
                userRegistrationDTO.getUsername() == null ||
                        userRegistrationDTO.getEmail() == null ||
                        userRegistrationDTO.getFullname() == null ||
                        userRegistrationDTO.getPassword() == null
        ){
            throw new NullPointerException("The request was malformed or missing required fields");

        }
        return ResponseEntity.ok(userService.register(userRegistrationDTO));
    }
    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(userService.authenticate(request));

    }

}
