package com.realcoders.realcoderlinkedinspring3.user;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getFullname(),
                user.getAge()
        );
    }
    public User fromDTO(UserDTO userDTO) {
        return new User(
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getFullname(),
                userDTO.getAge()
        );
    }


}
