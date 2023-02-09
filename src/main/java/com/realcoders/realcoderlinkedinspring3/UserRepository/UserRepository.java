package com.realcoders.realcoderlinkedinspring3.UserRepository;

import com.realcoders.realcoderlinkedinspring3.user.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository {
    void save (User user);

    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

}
