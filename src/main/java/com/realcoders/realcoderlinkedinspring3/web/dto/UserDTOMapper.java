package com.realcoders.realcoderlinkedinspring3.web.dto;

import com.realcoders.realcoderlinkedinspring3.service.user.UserPasswordEncoder;
import com.realcoders.realcoderlinkedinspring3.storage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserRegistrationDTO> {

    private final UserPasswordEncoder userPasswordEncoder;

    private static Integer idCounter = 1;

    private Integer generateId(){
        return idCounter++;
    }

    @Autowired
    public UserDTOMapper(UserPasswordEncoder userPasswordEncoder) {
        this.userPasswordEncoder = userPasswordEncoder;
    }

    @Override
    public UserRegistrationDTO apply(User user) {
        return new UserRegistrationDTO(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getFullname(),
                user.getAge()
        );
    }


    public User fromDTO(UserRegistrationDTO userRegistrationDTO) {
        User user = new User(
                generateId(),
                userRegistrationDTO.getUsername(),
                userRegistrationDTO.getEmail(),
                userPasswordEncoder.encode(userRegistrationDTO.getPassword()),
                userRegistrationDTO.getFullname(),
                userRegistrationDTO.getAge()
        );
        return user;
    }


}
