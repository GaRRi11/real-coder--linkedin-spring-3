package com.realcoders.realcoderlinkedinspring3.service.user;

import com.realcoders.realcoderlinkedinspring3.core.exceptions.UserNotFoundException;
import com.realcoders.realcoderlinkedinspring3.storage.model.User;
import com.realcoders.realcoderlinkedinspring3.storage.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationManager {

    private final UserRepository userRepository;
    private final UserPasswordEncoder userPasswordEncoder;

    @Autowired
    public UserAuthenticationManager(UserRepository userRepository, UserPasswordEncoder userPasswordEncoder) {
        this.userRepository = userRepository;
        this.userPasswordEncoder = userPasswordEncoder;
    }

    public boolean authenticate (String username, String password){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("Username Is Incorrect"));
        return user.getPassword().equals(userPasswordEncoder.encode(password));
    }


}
