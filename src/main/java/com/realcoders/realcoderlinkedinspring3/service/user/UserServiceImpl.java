package com.realcoders.realcoderlinkedinspring3.service.user;

import com.realcoders.realcoderlinkedinspring3.core.exceptions.InvalidPasswordException;
import com.realcoders.realcoderlinkedinspring3.storage.model.User;
import com.realcoders.realcoderlinkedinspring3.storage.repository.user.UserRepository;
import com.realcoders.realcoderlinkedinspring3.web.dto.AuthenticationRequest;
import com.realcoders.realcoderlinkedinspring3.web.response.AuthenticationResponse;
import com.realcoders.realcoderlinkedinspring3.web.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserAuthenticationManager userAuthenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           JwtService jwtService,
                           UserAuthenticationManager userAuthenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userAuthenticationManager = userAuthenticationManager;
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername (String username){
        return userRepository.findByUsername(username);
    }

    public void register(User user) throws NoSuchAlgorithmException {
        userRepository.save(user);
        var jwtToken = jwtService.
                generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws NoSuchAlgorithmException{
        if (!userAuthenticationManager.authenticate(request.getUsername(),request.getPassword())){
            throw new InvalidPasswordException("Password Is Incorrect");
        }
        var user = findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
        return response;
    }
}
