package com.realcoders.realcoderlinkedinspring3.UserRepository;

import com.realcoders.realcoderlinkedinspring3.UserRepository.UserRepository;
import com.realcoders.realcoderlinkedinspring3.db.InMemoryDb;
import com.realcoders.realcoderlinkedinspring3.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final InMemoryDb inMemoryDb;

    @Autowired
    public UserRepositoryImpl(InMemoryDb inMemoryDb) {
        this.inMemoryDb = inMemoryDb;
    }

    @Override
    public void save(User user) {
         inMemoryDb.insert(user);
        System.out.println(findById(1).toString());
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.of(inMemoryDb.findById(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        for (Integer i = 1; i <= inMemoryDb.savedCustomersSize(); i++) {
            if (inMemoryDb.findById(i).getEmail().equals(email)){
                return Optional.of(inMemoryDb.findById(i));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        for (Integer i = 1; i <= inMemoryDb.savedCustomersSize(); i++) {
            if (inMemoryDb.findById(i).getUsername().equals(username)){
                return Optional.of(inMemoryDb.findById(i));
            }
        }
        return Optional.empty();
    }


}
