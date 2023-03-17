package com.realcoders.realcoderlinkedinspring3.web.controller;

import com.realcoders.realcoderlinkedinspring3.core.exceptions.EmailAlreadyExistsException;
import com.realcoders.realcoderlinkedinspring3.core.exceptions.NullPointerException;
import com.realcoders.realcoderlinkedinspring3.service.user.UserService;
import com.realcoders.realcoderlinkedinspring3.service.user.UserServiceImpl;
import com.realcoders.realcoderlinkedinspring3.web.dto.AuthenticationRequest;
import com.realcoders.realcoderlinkedinspring3.web.dto.UserDTOMapper;
import com.realcoders.realcoderlinkedinspring3.web.dto.UserRegistrationDTO;
import com.realcoders.realcoderlinkedinspring3.web.response.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class AuthController {

    private final UserService userService;

    private final UserDTOMapper userDTOMapper;

    public AuthController(UserServiceImpl userServiceImpl, UserService userService, UserDTOMapper userDTOMapper) {
        this.userService = userService;
        this.userDTOMapper = userDTOMapper;
    }

    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> register(@RequestBody UserRegistrationDTO userRegistrationDTO)
            throws NoSuchAlgorithmException {
        if (
                userRegistrationDTO.getUsername() == null ||
                        userRegistrationDTO.getEmail() == null ||
                        userRegistrationDTO.getFullname() == null ||
                        userRegistrationDTO.getPassword() == null
        ) {
            throw new NullPointerException("The request was malformed or missing required fields");

        }
        if (userService.findByEmail(userRegistrationDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("The specified username or email already exists");
        }
        userService.register(userDTOMapper.fromDTO(userRegistrationDTO));
        return ResponseEntity.ok("User created successfully");
//        return "The user was successfully registered" + "  token: " + token;

    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws NoSuchAlgorithmException {
        if (
                request.getUsername() == null ||
                        request.getPassword() == null
        ) {
            throw new NullPointerException("The request was malformed or missing required fields");
        }
        return ResponseEntity.ok(userService.authenticate(request));

    }
}
