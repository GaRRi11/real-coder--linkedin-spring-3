package com.realcoders.realcoderlinkedinspring3.service.user;

import com.realcoders.realcoderlinkedinspring3.storage.model.User;
import com.realcoders.realcoderlinkedinspring3.web.dto.AuthenticationRequest;
import com.realcoders.realcoderlinkedinspring3.web.response.AuthenticationResponse;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface UserService {

    Optional<User> findById (Integer id);
    Optional<User> findByUsername (String username);
    Optional<User> findByEmail (String email);
    void register (User user) throws NoSuchAlgorithmException;
    AuthenticationResponse authenticate (AuthenticationRequest request) throws NoSuchAlgorithmException;

}
