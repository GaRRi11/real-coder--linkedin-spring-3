package com.realcoders.realcoderlinkedinspring3.config;

import com.realcoders.realcoderlinkedinspring3.UserRepository.UserRepository;
import com.realcoders.realcoderlinkedinspring3.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Autowired
    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("The provided username and password combination is incorrect"));
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserRepository userRepository(){
//        return new UserRepository() {
//            private InMemoryDb inMemoryDb;
//            @Override
//            public void save(User user) {
//                inMemoryDb.insert(user);
//            }
//
//            @Override
//            public Optional<User> findById(Integer id) {
//                return Optional.of(inMemoryDb.findById(id));
//            }
//
//            @Override
//            public Optional<User> findByEmail(String email) {
//                for (int i = 1; i <= inMemoryDb.savedCustomersSize(); i++) {
//                    if (inMemoryDb.findById(i).getEmail().equals(email)){
//                        return Optional.of(inMemoryDb.findById(i));
//                    }
//                }
//                return Optional.empty();
//            }
//        }
//    }
}
