package com.realcoders.realcoderlinkedinspring3.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserDTOMapper userDTOMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }
    public void save(UserDTO userDTO){
         userRepository.save(userDTOMapper.fromDTO(userDTO));
    }
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
