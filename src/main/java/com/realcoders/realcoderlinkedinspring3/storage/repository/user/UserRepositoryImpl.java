package com.realcoders.realcoderlinkedinspring3.storage.repository.user;

import com.realcoders.realcoderlinkedinspring3.storage.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @PostConstruct
    public void createTable() {
        final String sql =
                "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, username VARCHAR(255), email VARCHAR(255),password VARCHAR(255),fullname VARCHAR(255),age INT)";
        jdbcTemplate.execute(sql);
    }

    @PreDestroy
    public void dropTable(){
        jdbcTemplate.execute("DROP TABLE users");
    }

    @Override
    public void save(User user) {
        final String sql = "INSERT INTO users (username, email,password,fullname,age) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword(),user.getFullname(),user.getAge());
    }

    @Override
    public Optional<User> findById(Integer id) {
        final String sql = "SELECT * FROM users WHERE id = ?";
        return getUser(id, sql).stream().findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email){
        final String sql = "SELECT * FROM users WHERE email = ?";
        return getUser(email, sql).stream().findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        final String sql = "SELECT * FROM users WHERE username = ?";
        return getUser(username, sql).stream().findFirst();
    }

    private List<User> getUser(Object parameter, String sql) {
        return jdbcTemplate.query(sql, new Object[]{parameter}, new UserRowMapper());
    }

}
