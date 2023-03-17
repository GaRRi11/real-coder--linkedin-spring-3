package com.realcoders.realcoderlinkedinspring3.storage.repository.user;

import com.realcoders.realcoderlinkedinspring3.storage.model.User;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("fullname"),
                rs.getInt("age")
        );
    }
}
