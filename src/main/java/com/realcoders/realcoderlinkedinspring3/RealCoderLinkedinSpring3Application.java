package com.realcoders.realcoderlinkedinspring3;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class RealCoderLinkedinSpring3Application {

    public static void main(String[] args) throws NoSuchAlgorithmException, JsonProcessingException {
        SpringApplication.run(RealCoderLinkedinSpring3Application.class, args);

//
//          JwtService jwtService = new JwtService();
//        System.out.println(jwtService.extractExpiration("eyJhbGciOiJIUzI1NiIsInR5cCI6Ik1ZVE9LRU4ifQ.eyJzdWIiOiIxIiwidXNlcm5hbWUiOiJhc3EiLCJleHBpcmVzQXQiOiI5Njc3OTMxNzUwNjcxIn0.ryTW78RFogmNrb2z94drJtLu2dywDJJ9DDEw5N909gg"));
//        System.out.println(jwtService.isTokenExpired("eyJhbGciOiJIUzI1NiIsInR5cCI6Ik1ZVE9LRU4ifQ.eyJzdWIiOiIxIiwidXNlcm5hbWUiOiJhc3EiLCJleHBpcmVzQXQiOiI5Njc3OTMxNzUwNjcxIn0.ryTW78RFogmNrb2z94drJtLu2dywDJJ9DDEw5N909gg"));
//        User user = new User("maxmud","elibekiani","pass","full",18);
//        String m = jwtService.generateToken(user);
//        System.out.println(m);
//        System.out.println(jwtService.decode(m));
//        System.out.println(jwtService.extractUsername(m));
//        System.out.println(jwtService.extractExpiration(m));
//        System.out.println(jwtService.isTokenValid(m,user));
//        System.out.println(jwtService.isTokenExpired(m));
    }

}
