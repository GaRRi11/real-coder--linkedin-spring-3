package com.realcoders.realcoderlinkedinspring3.user;

import com.realcoders.realcoderlinkedinspring3.db.InMemoryDb;
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
         inMemoryDb.savedCustomers.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.of(inMemoryDb.savedCustomers.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        for (int i = 1; i <= inMemoryDb.savedCustomers.size(); i++) {
            if (inMemoryDb.savedCustomers.get(i).getEmail().equals(email)){
                return Optional.of(inMemoryDb.savedCustomers.get(i));
            }
        }
        return Optional.empty();
    }


}
