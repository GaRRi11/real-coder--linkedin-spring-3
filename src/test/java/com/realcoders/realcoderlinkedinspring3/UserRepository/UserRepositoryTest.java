//package com.realcoders.realcoderlinkedinspring3.UserRepository;
//
//import com.realcoders.realcoderlinkedinspring3.storage.model.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//
//@SpringBootTest
//class UserRepositoryTest {
//
//    @Autowired
//    private  UserRepository userRepository;
//
//    @Test
//    void findById() {
//        User user = new User(
//                "username",
//                "email",
//                "pass",
//                "full",
//                18
//        );
//
//        userRepository.save(user);
//
//        boolean expected = userRepository.findById(1).isPresent();
//
//        assertThat(expected, is(true));
//
//    }
//
//    @Test
//    void findByEmail() {
//        User user = new User(
//                "username",
//                "email",
//                "pass",
//                "full",
//                18
//        );
//
//        userRepository.save(user);
//
//        boolean expected = userRepository.findByEmail("email").isPresent();
//
//        assertThat(expected, is(true));
//    }
//
//    @Test
//    void findByUsername() {
//
//        User user = new User(
//                "username",
//                "email",
//                "pass",
//                "full",
//                18
//        );
//
//        userRepository.save(user);
//
//        boolean expected = userRepository.findByUsername("username").isPresent();
//
//        assertThat(expected, is(true));
//    }
//}