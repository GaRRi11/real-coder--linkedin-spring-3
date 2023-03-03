package com.realcoders.realcoderlinkedinspring3.controller;

import com.realcoders.realcoderlinkedinspring3.UserService.UserPasswordEncoder;
import com.realcoders.realcoderlinkedinspring3.user.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserRegistrationDTO> {

    private final UserPasswordEncoder userPasswordEncoder;

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


    public User fromDTO(UserRegistrationDTO userRegistrationDTODTO) {
        return new User(
                userRegistrationDTODTO.getUsername(),
                userRegistrationDTODTO.getEmail(),
                userPasswordEncoder.encode(userRegistrationDTODTO.getPassword()),
                userRegistrationDTODTO.getFullname(),
                userRegistrationDTODTO.getAge()
        );
    }


}
