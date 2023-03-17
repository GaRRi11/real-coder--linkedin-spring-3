//package com.realcoders.realcoderlinkedinspring3.UserService;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.realcoders.realcoderlinkedinspring3.controller.AuthenticationRequest;
//import com.realcoders.realcoderlinkedinspring3.controller.AuthenticationResponse;
//import com.realcoders.realcoderlinkedinspring3.controller.UserDTOMapper;
//import com.realcoders.realcoderlinkedinspring3.controller.UserRegistrationDTO;
//import com.realcoders.realcoderlinkedinspring3.core.exceptions.InvalidPasswordException;
//import com.realcoders.realcoderlinkedinspring3.core.exceptions.UserNotFoundException;
//import com.realcoders.realcoderlinkedinspring3.jwtService.JwtService;
//import com.realcoders.realcoderlinkedinspring3.storage.model.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.security.NoSuchAlgorithmException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//
//
//@SpringBootTest
//class UserServiceTest {
//
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private JwtService jwtService;
//    @Autowired
//    private UserDTOMapper userDTOMapper;
//
//    @Test
//    void register() throws NoSuchAlgorithmException, JsonProcessingException {
//        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
//                "username",
//                "email",
//                "pass",
//                "fullname",
//                18
//        );
//        User user = userDTOMapper.fromDTO(userRegistrationDTO);
//        AuthenticationResponse response = userService.register(user);
//        boolean expected = jwtService.isTokenValid(response.getToken(),user);
//        assertThat(expected, is(true));
//    }
//
//    @Test
//    void authenticateWithInvalidUsername() throws NoSuchAlgorithmException {
//        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
//                "username",
//                "email",
//                "pass",
//                "fullname",
//                18
//        );
//        User user = userDTOMapper.fromDTO(userRegistrationDTO);
//        userService.register(user);
//        AuthenticationRequest invalidUsernameRequest = new AuthenticationRequest("invalid", "pass");
//        assertThrows(UserNotFoundException.class, () -> {
//            userService.authenticate(invalidUsernameRequest);
//        });
//    }
//
//
//    @Test
//    void authenticateWithInvalidPassword() throws NoSuchAlgorithmException {
//        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
//                "username",
//                "email",
//                "pass",
//                "fullname",
//                18
//        );
//        User user = userDTOMapper.fromDTO(userRegistrationDTO);
//        userService.register(user);
//        AuthenticationRequest invalidPasswordRequest = new AuthenticationRequest("username", "invalid");
//        assertThrows(InvalidPasswordException.class, () -> {
//            userService.authenticate(invalidPasswordRequest);
//        });
//    }
//    @Test
//    void authenticate() throws NoSuchAlgorithmException, JsonProcessingException {
//        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(
//                "username",
//                "email",
//                "pass",
//                "fullname",
//                18
//        );
//        User user = userDTOMapper.fromDTO(userRegistrationDTO);
//        userService.register(user);
//        AuthenticationRequest validRequest = new AuthenticationRequest("username","pass");
//        AuthenticationResponse response = userService.authenticate(validRequest);
//        boolean expected = jwtService.isTokenValid(response.getToken(),user);
//        assertThat(expected,is(true));
//    }
//}