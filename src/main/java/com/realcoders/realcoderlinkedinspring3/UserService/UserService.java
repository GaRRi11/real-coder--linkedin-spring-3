package com.realcoders.realcoderlinkedinspring3.UserService;

import com.realcoders.realcoderlinkedinspring3.UserRepository.UserRepository;
import com.realcoders.realcoderlinkedinspring3.controller.AuthenticationRequest;
import com.realcoders.realcoderlinkedinspring3.controller.AuthenticationResponse;
import com.realcoders.realcoderlinkedinspring3.controller.UserRegistrationDTO;
import com.realcoders.realcoderlinkedinspring3.jwtService.JwtService;
import com.realcoders.realcoderlinkedinspring3.user.User;
import com.realcoders.realcoderlinkedinspring3.controller.UserDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserDTOMapper userDTOMapper;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper, JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }
    public String save(User user){
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return jwtToken;
    }
    public AuthenticationResponse authenticate(User user){
        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
        return response;
    }
    public AuthenticationResponse register(UserRegistrationDTO request) {
        var user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullname(request.getFullname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAge(request.getAge());
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
        return response;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
        return response;
    }
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername (String username){
        return userRepository.findByUsername(username);
    }
}
