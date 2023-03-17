package com.realcoders.realcoderlinkedinspring3.storage.repository.user;

import com.realcoders.realcoderlinkedinspring3.storage.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository {
    void save (User user);

    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

}
